package com.example.composenewapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.savedstate.compose.serialization.serializers.MutableStateSerializer
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator

class NavigationState(
    val startRoute: NavKey,
    topLevelRoute: MutableState<NavKey>,
    val backStacks: Map<NavKey, NavBackStack<NavKey>>
) {
    var topLevelRoute by topLevelRoute

    val stackInUse : List<NavKey>
        get() = if (topLevelRoute == startRoute) {
            listOf(startRoute)
        } else {
            listOf(startRoute, topLevelRoute)
        }
}


@Composable
fun NavigationState.toEntities(
    entryProvider:(NavKey)-> NavEntry<NavKey>
) : SnapshotStateList<NavEntry<NavKey>>{
    val decoratedEntries = backStacks.mapValues { (_,stack)->
        val decoratedEntries = listOf(
            rememberSaveableStateHolderNavEntryDecorator<NavKey>(),
            rememberViewModelStoreNavEntryDecorator<NavKey>()
        )

        rememberDecoratedNavEntries(
            backStack = stack,
            entryDecorators=decoratedEntries,
            entryProvider=entryProvider
        )
    }

    return stackInUse.flatMap { navKey->
       decoratedEntries[navKey]?: emptyList()
    }.toMutableStateList()

}


val serializerConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(MainScreenRoute.ChatScreen::class, MainScreenRoute.ChatScreen.serializer())
            subclass(MainScreenRoute.Calendar::class, MainScreenRoute.Calendar.serializer())
            subclass(MainScreenRoute.MoreScreen::class, MainScreenRoute.MoreScreen.serializer())
        }
    }
}


@Composable
fun rememberNavigationState(
    startRoute: NavKey,
    topLevelRouts: Set<NavKey>
): NavigationState {
    val topLevelRoute = rememberSerializable(
        startRoute,
        topLevelRouts,
        configuration =serializerConfig,
        serializer = MutableStateSerializer(PolymorphicSerializer(NavKey::class))
    ){
        mutableStateOf(startRoute)
    }

    val backStacks = topLevelRouts.associateWith { key->
        rememberNavBackStack(
            configuration = serializerConfig, key
        )
    }

    return remember (startRoute, topLevelRoute.value,backStacks){
        NavigationState(
            startRoute,topLevelRoute,backStacks
        )
    }

}