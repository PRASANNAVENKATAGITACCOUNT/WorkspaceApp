package com.learning.features.presentation.app_navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.learning.features.data.local.TaskEntity
import com.learning.features.presentation.todo.CreateToDoUI
import com.learning.features.presentation.todo.ToDoViewModel
import com.learning.features.presentation.todo.To_DoMainScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ToDoAppNavigation(onBackPress:()-> Unit={}) {
    val toDoViewModel = koinViewModel<ToDoViewModel>()
    val tasksList = toDoViewModel.taskList.collectAsState()
    val selectedTask = toDoViewModel.selectedTask.collectAsState()

    val backStackList = rememberNavBackStack(
        elements = arrayOf<NavKey>(
        AppRoutes.TodoRoutes.TodoList,
            )
    )
    NavDisplay(
        backStack = backStackList,
        entryProvider=entryProvider {
            entry<AppRoutes.TodoRoutes.TodoList> {
                To_DoMainScreen(
                    tasksList.value,
                    createNewTask = {
                        backStackList.add(AppRoutes.TodoRoutes.TodoDetail(0))
                    },
                    onClickOfTask = { task ->
                        backStackList.add(AppRoutes.TodoRoutes.TodoDetail(task.id))
                        toDoViewModel.setSelectedTask(task)
                    },
                    onBackPress = {
                        onBackPress.invoke()
                    }
                )
            }

            entry<AppRoutes.TodoRoutes.TodoDetail> { taskId: NavKey->
                CreateToDoUI(
                    selectedTask.value,
                    onTaskNameChange = { taskName ->
                        selectedTask.value.let { task ->
                            val newTask = task.copy(taskName = taskName)
                            toDoViewModel.setSelectedTask(newTask)
                        }
                    },
                    onTaskDescriptionChange = { description ->
                        selectedTask.value.let { task ->
                            val newTask = task.copy(taskDescription = description)
                            toDoViewModel.setSelectedTask(newTask)
                        }
                    },
                    onSave = { task ->
                        toDoViewModel.insertTask(task)
                        backStackList.removeLastOrNull()
                    },
                    onCancel = {
                        toDoViewModel.setSelectedTask(TaskEntity())
                        backStackList.removeLastOrNull()
                    }
                )
            }
        }
    )
}

sealed class TODO_SCREEN(val route: String){
    object ALL_TODOS: TODO_SCREEN("ALL_TODOS")

    object CREATE_TODO: TODO_SCREEN("CREATE_TODO")
}

@Preview(showBackground = true)
@Composable
fun ToDoAppNavPreview() {
    ToDoAppNavigation {

    }
}

