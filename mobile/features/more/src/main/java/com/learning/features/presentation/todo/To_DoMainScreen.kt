package com.learning.features.presentation.todo

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learning.features.data.local.TaskEntity

@Composable
fun To_DoMainScreen(listOfTaskEntity: List<TaskEntity>,
                    createNewTask:()-> Unit={},
                    onClickOfTask:(TaskEntity)-> Unit={},
                    onBackPress:()-> Unit={}
                    ) {
    BackHandler {
        onBackPress.invoke()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                createNewTask.invoke()
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    ) {innerPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .statusBarsPadding()
        ) {
            Text(text = "To Do List",
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                style = TextStyle(
                fontSize = 25.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
            ))
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(8.dp)
            ) {
                items(listOfTaskEntity){ task->
                    To_DoUI(task) {
                        onClickOfTask.invoke(task)
                    }
                }
            }

        }

    }
}

@Composable
fun To_DoUI(taskEntity: TaskEntity, onClickTask:(TaskEntity)-> Unit={}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .clickable {
                onClickTask.invoke(taskEntity)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "",
            modifier = Modifier.padding(5.dp)
        )

        Text(
            text = taskEntity.taskName,
            style = TextStyle(
                color = Color.LightGray,
                fontSize = 12.sp
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoPreview() {
        To_DoMainScreen(listOf(TaskEntity()))

}