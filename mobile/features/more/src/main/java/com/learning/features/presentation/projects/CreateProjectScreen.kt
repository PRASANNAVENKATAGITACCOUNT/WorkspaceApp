package com.learning.features.presentation.projects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learning.features.data.local.ProjectHolderOwnerEntity
import com.learning.features.data.local.TaskEntity
import com.learning.features.data.toTask
import com.learning.features.domain.Project
import com.learning.features.domain.Task
import com.learning.features.presentation.commonUI.BackNavTopBar
import com.learning.features.presentation.commonUI.InputFieldComponent
import com.learning.features.presentation.todo.To_DoUI

@Composable
fun CreateProjectScreen(
    projectEntity: Project = Project(),
    projectHolderOwnerEntity: ProjectHolderOwnerEntity = ProjectHolderOwnerEntity(),
    onProjectNameChange:(String)-> Unit={},
    navigateToMainScreen:()-> Unit={},
    navigateToCreateTodoScreen:(Task)-> Unit={},
    insertNewProject:()-> Unit={}
) {
    val listOfTaskEntity = listOf<TaskEntity>(
        TaskEntity(id=0, taskName = "To-do")
    )
    val map = mapOf<String, String>(
        "Status" to projectHolderOwnerEntity.projectStatus,
        "Associates" to "",
        "End Date" to ""
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BackNavTopBar(
                onBackPress = navigateToMainScreen
            )
        }
    ) {innerPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.fillMaxWidth().height(35.dp))
            InputFieldComponent(
                onValueChange = onProjectNameChange,
                textData = projectEntity.projectName,
                placeHolderText = "Project Name",
                showCursor = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
            )

            HeadingWithCustomUI(
                headingText = "About Project",
                customUI = {
                    InputFieldComponent(
                        onValueChange = {},
                        textData = "Provide an overview of the project's goals and context",
                        isMultiline = true,
                        showCursor = false,
                        fontSize = 18,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )

            HeadingWithCustomUI(
                headingText = "Action Items"
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(8.dp)
                ) {
                    items(listOfTaskEntity){ task->
                        To_DoUI(task) {task->
                            navigateToCreateTodoScreen(task.toTask())
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Button(onClick = {
                    insertNewProject.invoke()
                    navigateToMainScreen.invoke()
                }) {
                    Text(text = "SAVE")
                }
                Button(onClick = navigateToMainScreen
                ) {
                    Text(text = "CANCEL")
                }
            }


        }
    }
}

@Composable
fun HeadingWithCustomUI(headingText: String,
                        customUI: @Composable ()->Unit
                        ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = headingText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(8.dp))
        customUI()
    }
}


@Composable
fun ShowProjectDetails(modifier: Modifier = Modifier.fillMaxWidth(),
                        listOfProductDetails: Map<String, String>) {
    LazyRow(
        modifier=modifier.wrapContentHeight()
    ) {
        listOfProductDetails.forEach { (heading, detailData) ->
            item {
                ProjectDetailItem(
                    headingText = heading,
                    data = detailData
                )
            }
        }
    }
}

@Composable
fun ProjectDetailItem(
    modifier: Modifier = Modifier.wrapContentSize(),
    headingText: String,
    data: String
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text =headingText
        )
        Text(
            text =data
        )

    }
}

@Preview(showBackground = true)
@Composable
fun CreateProjectPreview() {
        CreateProjectScreen()

}