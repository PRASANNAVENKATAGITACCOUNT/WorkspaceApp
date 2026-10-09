package com.learning.features.presentation.app_navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoutes : NavKey {

    @Serializable
    class MainScreen: AppRoutes, NavKey{

    }

    @Serializable
    class TodoRoutes : AppRoutes, NavKey {


        @Serializable
        data object TodoList : AppRoutes, NavKey


        @Serializable
        data class TodoDetail(val id: Long): AppRoutes, NavKey

    }


    @Serializable
    class NotesRoutes: AppRoutes, NavKey{

        @Serializable
        data object NotesList: AppRoutes, NavKey {

        }

        @Serializable
        data class NotesDetails(val notesId: Long): AppRoutes, NavKey {

        }

    }


    @Serializable
    class ProjectRoutes: AppRoutes, NavKey{

        @Serializable
         class CreateProject: AppRoutes, NavKey{

        }

        @Serializable
        data object MainProjectScreen : AppRoutes, NavKey{

        }

        @Serializable
        class CreateProjectHolder: AppRoutes, NavKey{

        }
    }


}