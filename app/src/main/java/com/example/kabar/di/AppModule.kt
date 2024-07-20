package com.example.kabar.di

import android.app.Application
import androidx.room.ProvidedTypeConverter
import androidx.room.Room
import com.example.kabar.data.local.NewsDao
import com.example.kabar.data.local.NewsDataBase
import com.example.kabar.data.local.NewsTypeConvertor
import com.example.kabar.data.manager.LocalUserManagerImpl
import com.example.kabar.data.remote.NewsApi
import com.example.kabar.data.repository.NewsRepositotryImpl
import com.example.kabar.domain.manager.LocalUserManager
import com.example.kabar.domain.repository.NewsRepository
import com.example.kabar.domain.usercases.app_Entry.AppEntryUsesCases
import com.example.kabar.domain.usercases.app_Entry.ReadAppEntry
import com.example.kabar.domain.usercases.app_Entry.SaveAppEntry
import com.example.kabar.domain.usercases.news.DeleteArticle
import com.example.kabar.domain.usercases.news.GetNews
import com.example.kabar.domain.usercases.news.NewsUsesCases
import com.example.kabar.domain.usercases.news.SeachNews
import com.example.kabar.domain.usercases.news.SelArticles
import com.example.kabar.domain.usercases.news.SelectArticle
import com.example.kabar.domain.usercases.news.UpsertArticle
import com.example.kabar.util.Constants.BASE_URL
import com.example.kabar.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //->this to specify lifecyle of AppMoudle which is SingletonComponent which will remain till this app destroies
object AppModule {

    //providing our dependencies
    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUsesCases(localUserManager: LocalUserManager) = AppEntryUsesCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi():NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ):NewsRepository = NewsRepositotryImpl(newsApi, newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
    ): NewsUsesCases{
        return NewsUsesCases(
            getNews = GetNews(newsRepository),
            seachNews = SeachNews(newsRepository),
            selectArticle = SelectArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            selArticles = SelArticles(newsRepository)

        )
    }


    @Provides
    @Singleton
    fun provideNewsDataBase(
        application: Application
    ):NewsDataBase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDataBase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()//if you upgrade something to database
            .build()
    }

    //to provide dao also so...
    @Provides
    @Singleton
    fun provideNewsDao(
        newsDataBase: NewsDataBase
    ):NewsDao = newsDataBase.newsDao
}

