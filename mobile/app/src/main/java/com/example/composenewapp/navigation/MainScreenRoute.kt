package com.example.composenewapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute{

    @Serializable
    object AuthScreen: AppRoute, NavKey{

    }

    @Serializable
    object MainScreen: AppRoute, NavKey{

    }
    @Serializable
    object AIAssistent: MainScreenRoute, NavKey{

    }

}
@Serializable
sealed interface MainScreenRoute : NavKey {



    @Serializable
    object ChatScreen: MainScreenRoute, NavKey {
    }

    @Serializable
    object MoreScreen : MainScreenRoute, NavKey{

    }

    @Serializable
    object Calendar: MainScreenRoute, NavKey{

    }

}

data class BottomNavItem(
    val icon: ImageVector,
    val title: String
)

val TOP_LEVEL_DESTINATION = mapOf<MainScreenRoute, BottomNavItem>(
    MainScreenRoute.ChatScreen to BottomNavItem(
        icon = Icons.Default.Home,
        "Chat"
    ),
    MainScreenRoute.Calendar to BottomNavItem(
        icon = Icons.Filled.DateRange,
        "Calendar"
    ),
    MainScreenRoute.MoreScreen to BottomNavItem(
        icon = Icons.AutoMirrored.Filled.List,
        "More"
    )
)