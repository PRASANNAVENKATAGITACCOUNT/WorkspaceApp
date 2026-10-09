package com.learning.features.presentation.projects

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.learning.features.data.local.ProjectHolderOwnerEntity
import com.learning.features.presentation.commonUI.BackNavTopBar
import com.learning.features.presentation.commonUI.InputFieldComponent

@Composable
fun CreateProjectHolderScreen(
    project: ProjectHolderOwnerEntity= ProjectHolderOwnerEntity(),
    onProjectHolderNameChange:(String)-> Unit={},
    navigateToMainScreen:()-> Unit={},
    insertNewProject:()-> Unit={}
) {
    BackHandler {
        navigateToMainScreen.invoke()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BackNavTopBar {
                navigateToMainScreen.invoke()
            }
        }
    ) {innerPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputFieldComponent(
                onValueChange = {
                    onProjectHolderNameChange.invoke(it)
                },
                label = "Enter Project Holder Owner Name",
                textData = project.projectStatus,
                modifier = Modifier.fillMaxWidth().padding(0.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth().height(55.dp)
            ) {
                Button(onClick = {
                    insertNewProject.invoke()
                    navigateToMainScreen.invoke()
                }) {
                    Text(text = "SAVE")
                }
                Button(onClick = {
                    navigateToMainScreen.invoke()
                }) {
                    Text(text = "CANCEL")
                }
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateProjectHolderPreview() {

    CreateProjectHolderScreen {  }

}