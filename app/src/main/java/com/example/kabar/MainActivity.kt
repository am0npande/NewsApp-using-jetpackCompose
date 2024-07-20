package com.example.kabar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.kabar.presentation.nvGraph.NaviGraph
import com.example.kabar.ui.theme.KabarTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE)

        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        //WindowCompat.setDecorFitsSystemWindows(window,false)

        //we wanna keep splashscreen visible until we fetch startDestination from datastore prferences, so we use apply{} on splash screen
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.splashCondition// when it fetched this becomes false else true
            }
        }

//        lifecycleScope.launch{
//            appEntryUsesCases.readAppEntry().collect {
//                Log.d("Test", it.toString())
//            }
//        }

        setContent {
            KabarTheme {

                val isSystemInDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()
                val cachedColorScheme = colorScheme

                //when we have compose state in a uncomposed code and this will only exceuted when successful recmposition inncur

                Box(
                    modifier = Modifier
                        .background(color = cachedColorScheme.background)
                        .statusBarsPadding()
                        .navigationBarsPadding()
                ){
                    SideEffect {
                        systemController.setSystemBarsColor(
                            color = cachedColorScheme.background,
                            darkIcons = !isSystemInDarkMode
                        )
                    }
                    //val viewModel:OnBoardingViewModel = hiltViewModel()
                    //OnBoardingScreen(event = viewModel::onEvent)

                    val startDestination = viewModel.startDestinatio
                    NaviGraph(startDestination = startDestination)


                    //or use this instead of ::
//                    OnBoardingScreen(event = {
//                        viewModel.onEvent(it)
//                    })
                }
            }
        }
    }
}

