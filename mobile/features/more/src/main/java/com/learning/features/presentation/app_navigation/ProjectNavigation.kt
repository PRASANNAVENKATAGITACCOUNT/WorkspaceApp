package com.learning.features.presentation.app_navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.learning.features.domain.Project
import com.learning.features.presentation.projects.CreateProjectHolderScreen
import com.learning.features.presentation.projects.CreateProjectScreen
import com.learning.features.presentation.projects.MainProjectViewModel
import com.learning.features.presentation.projects.ProjectMainScreen
import org.koin.androidx.compose.koinViewModel


@Composable
fun ProjectNavigation () {
    val projectViewModel = koinViewModel<MainProjectViewModel>()
    val project = projectViewModel.project.collectAsState()
    val projectHolderOwner = projectViewModel.projectHolderOwner.collectAsState()
    val projectsHolderWithProjects = projectViewModel.projectHolderWithProject.collectAsState()

    val backStackList = rememberNavBackStack(
        elements = arrayOf<NavKey>(
            AppRoutes.ProjectRoutes.MainProjectScreen
        )
    )

    NavDisplay(
        backStack = backStackList,
        entryProvider = entryProvider {
            entry<AppRoutes.ProjectRoutes.MainProjectScreen>{
                ProjectMainScreen(
                    listOfProjectOwners = projectsHolderWithProjects.value,
                    navigateToCreateProject = {
                        backStackList.add(AppRoutes.ProjectRoutes.CreateProject())
                    },
                    navigateToEditProject = { projectId->

                    },
                    navigateToCreateProjectHolderOwner = {
                        backStackList.add(AppRoutes.ProjectRoutes.CreateProjectHolder())
                    }, moveProjectToNewProjectHolderOwner = { projectId, newHolderId ->
                        projectViewModel.moveProjectToNewProjectHolder(projectId, newHolderId)
                    },
                )
            }
            entry<AppRoutes.ProjectRoutes.CreateProject>{
                val selectedProject =
                    project.value?.copy(
                        projectHolderOwnerId =  if(projectsHolderWithProjects.value.isNotEmpty())
                        {
                            projectsHolderWithProjects.value[0].project.holderOwnerId
                        }else {
                            0L
                        }
                    ) ?: Project()

                CreateProjectScreen(
                    projectEntity = selectedProject,
                    projectHolderOwnerEntity = projectHolderOwner.value ,
                    onProjectNameChange = { newProjectName ->
                        var newProjectEntity: Project? = null
                        newProjectEntity = selectedProject.copy(projectName = newProjectName)
                        projectViewModel.updateProject(newProjectEntity)
                    }, navigateToMainScreen = {
                        backStackList.removeLastOrNull()
                    }, insertNewProject = {
                        projectViewModel.insertProject()
                    }
                )
            }
            entry<AppRoutes.ProjectRoutes.CreateProjectHolder>{
                CreateProjectHolderScreen(
                    project = projectHolderOwner.value,
                    onProjectHolderNameChange = { newProjectName ->
                        val newProjectOwner = projectHolderOwner.value.copy(
                            projectStatus = newProjectName
                        )
                        projectViewModel.updateProjectHolderOwner(
                            newProjectOwner
                        )
                    },
                    navigateToMainScreen = {
                        backStackList.removeLastOrNull()
                    },
                    insertNewProject = {
                        projectViewModel.createProjectHolderOwner()
                    }
                )
            }
        }
    )

}

@Preview(showBackground = true)
@Composable
fun ProjectNavPreview() {
    ProjectNavigation()

}