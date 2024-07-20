package com.example.kabar.presentation.onBoarding

//event class is a sealed class
//event which are sent from ui to viewmodel

sealed class OnBoardingEvent {
    object SaveAppEntry:OnBoardingEvent()
}