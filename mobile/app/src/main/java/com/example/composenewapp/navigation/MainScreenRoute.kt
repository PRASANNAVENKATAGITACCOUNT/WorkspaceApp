package com.example.composenewapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation3.runtime.NavKey
import com.example.composenewapp.R
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
    object AIAssistent: AppRoute, NavKey{

    }

}
@Serializable
sealed interface MainScreenRoute : NavKey {



    @Serializable
    object ChatScreen: MainScreenRoute, NavKey {
    }
    @Serializable
    object CallScreen: MainScreenRoute, NavKey {
    }

    @Serializable
    object MoreScreen : MainScreenRoute, NavKey{

    }

    @Serializable
    object Calendar: MainScreenRoute, NavKey{

    }

}

data class BottomNavItem(
    val icon: @Composable ()-> Painter,
    val title: String,
    )

val TOP_LEVEL_DESTINATION = mapOf<MainScreenRoute, BottomNavItem>(
    MainScreenRoute.ChatScreen to BottomNavItem(
        icon ={ painterResource(R.drawable.chat_icon)},
        "Chat"
    ),
    MainScreenRoute.CallScreen to BottomNavItem(
        icon ={ painterResource(R.drawable.call_icon)},
        "Call"
    ),
    MainScreenRoute.Calendar to BottomNavItem(
        icon = {painterResource(R.drawable.calendar_icon)},
        "Calendar"
    ),
    MainScreenRoute.MoreScreen to BottomNavItem(
        icon = {painterResource(R.drawable.more_icon)},
        "More"
    )
)