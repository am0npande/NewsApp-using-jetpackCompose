package com.example.kabar.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.kabar.domain.manager.LocalUserManager
import com.example.kabar.util.Constants
import com.example.kabar.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


//this is implementation of localusermanager

class LocalUserManagerImpl(
    private val context: Context
):LocalUserManager{
    override suspend fun saveAppEntry() {
        context.dataStore.edit {setting ->
            setting[PreferencesKEy.APP_ENTRY] = true
        }

    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map {
            preferences->

            preferences[PreferencesKEy.APP_ENTRY]?:false
        }
    }
}
//for data preferences we need something key value  //.extension value is .datastore
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PreferencesKEy{
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_EXTRY)
}

