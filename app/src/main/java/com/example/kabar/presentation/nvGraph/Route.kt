package com.example.kabar.presentation.nvGraph

sealed class Route(
    val route: String
) {
    object OnBoardingScreen: Route(route = "onBoardingscreen")
    object HomeScreen: Route(route = "homeScreen")
    object SeachScreen: Route(route = "searchScreen")
    object BookMarkScreen: Route(route = "bookMarkSreen")
    object DetailScreen: Route(route = "detailScreen")
    object AppStartNavigation: Route(route = "appStartNavigation")
    object NewsNavigation: Route(route = "newsNavigation")
    object NewsNavigationScreen: Route(route = "newsNavigationScreen")

}