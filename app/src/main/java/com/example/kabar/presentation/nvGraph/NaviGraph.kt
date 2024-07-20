package com.example.kabar.presentation.nvGraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.kabar.presentation.newsNavigator.NewsNavigator
import com.example.kabar.presentation.onBoarding.OnBoardingScreen
import com.example.kabar.presentation.onBoarding.OnBoardingViewModel

@Composable
fun NaviGraph(
    startDestination: String
) {
    val navController = rememberNavController()
    
    NavHost(navController = navController,startDestination = startDestination){
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ){
            composable(route = Route.OnBoardingScreen.route)
            {
                val viewModel:OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }
        
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigationScreen.route
        ){
            composable(route = Route.NewsNavigationScreen.route) {
                NewsNavigator()
            }
        }
    }
}