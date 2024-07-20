package com.example.kabar.presentation.newsNavigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.kabar.R
import com.example.kabar.domain.model.Article
import com.example.kabar.presentation.Search.SearchScreen
import com.example.kabar.presentation.Search.SearchViewModel
import com.example.kabar.presentation.bookmark.BookMarkViewModel
import com.example.kabar.presentation.bookmark.BookmarkScreen
import com.example.kabar.presentation.common.HomeScreen
import com.example.kabar.presentation.details.DetailViewModel
import com.example.kabar.presentation.details.DetailsEvent
import com.example.kabar.presentation.details.DetailsScreen
import com.example.kabar.presentation.home.HomeViewModel
import com.example.kabar.presentation.newsNavigator.component.BottomNavigationItem
import com.example.kabar.presentation.newsNavigator.component.NewsBarOnNavigation
import com.example.kabar.presentation.nvGraph.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigator(){

    val bottomNavigationItem = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "BookMark")
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    selectedItem = remember(key1 = backStackState) {

        when (backStackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SeachScreen.route -> 1
            Route.BookMarkScreen.route -> 2
            else -> 0
        }
    }

    val isBottomBarVisible = remember(key1 = backStackState){
        backStackState?.destination?.route == Route.HomeScreen.route ||
        backStackState?.destination?.route == Route.SeachScreen.route ||
        backStackState?.destination?.route == Route.BookMarkScreen.route

    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBarOnNavigation(
                    items = bottomNavigationItem,
                    selected = selectedItem,
                    OnItemCLicked = { index ->
                        when (index) {
                            0 -> navigationToTap(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> navigationToTap(
                                navController = navController,
                                route = Route.SeachScreen.route
                            )

                            2 -> navigationToTap(
                                navController = navController,
                                route = Route.BookMarkScreen.route
                            )


                        }
                    }
                )
            }
        }
    ) {
        val bottomPadding = it
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottomPadding)
        ){
            composable(route = Route.HomeScreen.route){
                val viewModel:HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,

                    navigateToSearch = { navigationToTap(navController = navController,route = Route.SeachScreen.route) },

                    navigateToDetails = { article -> navigateToDetails(navController = navController, article = article) }
                )
            }
            composable(route = Route.SeachScreen.route){
                val viewModel:SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(state = state, event = viewModel::onEventss,
                    navigateToDetails = { navigateToDetails(
                    navController = navController,
                    article = it
                ) })
            }

            composable(route = Route.DetailScreen.route){
                val viewModel: DetailViewModel = hiltViewModel()

                //TODO: handle side effect
                if(viewModel.sideEffect!= null) {
                    Toast.makeText(LocalContext.current,viewModel.sideEffect,Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let {
                    DetailsScreen(article = it, event = viewModel::onEvent, navigateUp = {navController.navigateUp()})
                }
            }

            composable(route = Route.BookMarkScreen.route){
                val viewModel: BookMarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookmarkScreen(
                    state = state,
                    navigateToDetails = { article -> navigateToDetails(
                    navController = navController,
                    article = article) } )
            }
        }
    }
}

fun navigationToTap(navController: NavController,route: String){
    navController.navigate(route){

        navController.graph.startDestinationRoute?.let { homeScreen ->

            popUpTo(homeScreen){
                saveState = true
            }
        }
        restoreState = true
        launchSingleTop = true //not create new instance when we click multiple time on icons
    }
}

//if you wanna pass data in compose navigation system we can pass only primitive data ,to pass an object we can take help of backStackentry
private fun navigateToDetails(navController: NavController, article: Article){
    navController.currentBackStackEntry?.savedStateHandle?.set("article",article)
    navController.navigate(
        route = Route.DetailScreen.route
    )
}