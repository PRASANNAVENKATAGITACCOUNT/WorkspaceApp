package com.learning.features.presentation.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.learning.features.data.local.TaskEntity
import com.learning.features.presentation.commonUI.BackNavTopBar
import com.learning.features.presentation.commonUI.InputFieldComponent

@Composable
fun CreateToDoUI(taskEntity: TaskEntity = TaskEntity(),
                 onTaskNameChange:(String)->Unit={},
                 onTaskDescriptionChange:(String)-> Unit={},
                 onSave:(TaskEntity)-> Unit={},
                 onCancel:()-> Unit={}) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BackNavTopBar()
        }
    ) {innerPadding->

        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            InputFieldComponent(
                label = "Task Name",
                textData = taskEntity.taskName,
                fontSize = 25,
                onValueChange = {
                    onTaskNameChange.invoke(it)
                },)

            InputFieldComponent(
                label = "Task Description",
                textData = taskEntity.taskDescription,
                fontSize = 18,
                onValueChange = {
                    onTaskDescriptionChange.invoke(it)
                }, isMultiline = true)



            Row(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = {

                    onSave.invoke(taskEntity)
                }, modifier = Modifier.padding(10.dp)) {
                    Text(text = "SAVE")
                }

                Button(onClick = {
                    onCancel.invoke()
                }, modifier = Modifier.padding(10.dp)) {
                    Text(text = "CANCEL")
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun CreateToDoUI() {
    CreateToDoUI(TaskEntity())

}