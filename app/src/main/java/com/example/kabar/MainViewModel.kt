package com.example.kabar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kabar.domain.usercases.app_Entry.AppEntryUsesCases
import com.example.kabar.presentation.nvGraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUsesCases: AppEntryUsesCases
): ViewModel(){

    var splashCondition by mutableStateOf(true)
        private set

    var startDestinatio by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        appEntryUsesCases.readAppEntry().onEach { shouldStartFromHomeScreen ->

            if(shouldStartFromHomeScreen){
                startDestinatio = Route.NewsNavigation.route
            }
            else{
                startDestinatio = Route.AppStartNavigation.route
            }

            delay(300)
            splashCondition = false

        }.launchIn(viewModelScope)//collecting this flow
    }
}