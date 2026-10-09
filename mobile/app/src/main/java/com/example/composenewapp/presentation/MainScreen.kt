package com.example.composenewapp.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.composenewapp.navigation.Navigator
import com.example.composenewapp.navigation.MainScreenRoute
import com.example.composenewapp.navigation.TOP_LEVEL_DESTINATION
import com.example.composenewapp.navigation.rememberNavigationState
import com.example.composenewapp.navigation.toEntities
import com.example.composenewapp.ui.theme.ComposeNewAppTheme
import com.learning.features.presentation.app_navigation.MoreFeatureNavigation

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState(
        startRoute = MainScreenRoute.ChatScreen,
        topLevelRouts = TOP_LEVEL_DESTINATION.keys
    )

    val navigator = remember {
        Navigator(navigationState)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AppBottomBar(
                selectedKey = navigationState.topLevelRoute,
                navigateToScreen = {
                    navigator.navigate(it)
                }
            )
        }
    ) {innerPadding->
        NavDisplay(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            onBack = navigator::goBack,
            entries = navigationState.toEntities(
                entryProvider = entryProvider {
                    entry <MainScreenRoute.ChatScreen>{
                        Text("Chat")
                    }
                    entry <MainScreenRoute.Calendar>{
                        Text("Calendar")
                    }
                    entry <MainScreenRoute.MoreScreen>{
                        MoreFeatureNavigation()
                    }
                }
            )
        )
    }

}


@Composable
fun AppBottomBar(
    selectedKey: NavKey,
    navigateToScreen: (NavKey)-> Unit,
    modifier: Modifier= Modifier
) {
    BottomAppBar(
        modifier = modifier.fillMaxWidth(),
    ) {
        TOP_LEVEL_DESTINATION.forEach { (navkey, bottomNavItem) ->
            NavigationBarItem(
                selected = navkey == selectedKey,
                onClick = {
                    navigateToScreen(navkey)
                },
                icon = {
                    Icon(
                        imageVector = bottomNavItem.icon,
                        contentDescription = ""
                    )
                },
                label = {
                    Text(text = bottomNavItem.title)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ComposeNewAppTheme {
        MainScreen()
    }
}