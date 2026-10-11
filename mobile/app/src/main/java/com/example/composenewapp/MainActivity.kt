package com.example.composenewapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.composenewapp.navigation.AppRoute
import com.example.composenewapp.presentation.MainScreen
import com.example.composenewapp.ui.theme.ComposeNewAppTheme
import com.learning.auth_ui.UserAuthScreen


class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeNewAppTheme {
                val appBackStack = rememberNavBackStack(AppRoute.AuthScreen)

                NavDisplay(
                    modifier = Modifier.fillMaxSize(),
                    backStack = appBackStack,
                    onBack = {},
                    entryProvider = entryProvider {
                        entry <AppRoute.AuthScreen>{
                            UserAuthScreen {
                                appBackStack.add(AppRoute.MainScreen)
                            }
                        }
                        entry <AppRoute.MainScreen>{
                            MainScreen()
                        }
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    ComposeNewAppTheme() {

    }
}

/*Transition compose code
* enterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { it } // comes from right
                        ) + fadeIn()
                    } ,
                    exitTransition = {
                        ExitTransition.None
                    },
                    popEnterTransition = {
                        EnterTransition.None
                    },
                    popExitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { it } // exits to right
                        ) + fadeOut()
                    }
* */