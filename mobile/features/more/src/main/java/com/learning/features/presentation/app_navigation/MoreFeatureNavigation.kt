package com.learning.features.presentation.app_navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.learning.features.presentation.APP_FEATURES
import com.learning.features.presentation.MoreScreen

@Composable
fun MoreFeatureNavigation() {
    val backStackList = rememberNavBackStack(
        elements = arrayOf<NavKey>(
            AppRoutes.MainScreen()
        )
    )
    NavDisplay(
        backStack= backStackList,
        entryProvider = entryProvider {

            entry<AppRoutes.MainScreen> {
                MoreScreen{ feature->
                    when(feature){
                        APP_FEATURES.PROJECT_FEATURE.featureName->{
                            backStackList.add(AppRoutes.ProjectRoutes())
                        }
                        APP_FEATURES.TO_DO_LIST_FEATURE.featureName ->{
                            backStackList.add(AppRoutes.TodoRoutes())
                        }
                        APP_FEATURES.NOTES_FEATURE.featureName ->{
                            backStackList.add(AppRoutes.NotesRoutes())
                        }
                    }
                }
            }
            entry<AppRoutes.ProjectRoutes> {
                ProjectNavigation()
            }

            entry<AppRoutes.TodoRoutes> {
                ToDoAppNavigation{
                    backStackList.removeLastOrNull()
                }
            }
            entry<AppRoutes.NotesRoutes> {
                NotesAppNavigation{
                    backStackList.removeLastOrNull()
                }
            }

        }
    )
}