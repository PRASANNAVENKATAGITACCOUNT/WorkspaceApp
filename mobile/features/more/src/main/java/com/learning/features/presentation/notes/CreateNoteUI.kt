package com.learning.features.presentation.notes

import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.learning.features.data.local.NoteEntity
import com.learning.features.presentation.commonUI.BackNavTopBar
import com.learning.features.presentation.commonUI.InputFieldComponent

@Composable
fun CreateNoteUI(noteEntity: NoteEntity,
                 onTitleChange:(String)->Unit={},
                 onDescriptionChange:(String)-> Unit={},
                 onSave:(NoteEntity)-> Unit={},
                 onCancel:()->Unit={}) {

    val colors = listOf(
        Color.Red,
        Color.Green,
        Color.LightGray,
        Color.Blue,
        Color.Cyan,
        Color.Magenta
    )

    BackHandler(enabled = true) {
     onCancel.invoke()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BackNavTopBar()
        }
    )
    {innerPadding->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            InputFieldComponent(
                label = "Title",
                textData = noteEntity.title,
                fontSize = 25,
                onValueChange = {
                onTitleChange.invoke(it)
                })

            InputFieldComponent(label = "Description",
                textData = noteEntity.description,
                fontSize = 18,
                onValueChange = {
                    onDescriptionChange.invoke(it)
                },
                isMultiline = true)

            Row(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = {
                    val randomColor = (0..<colors.size-1).random()
                    val selectedNote = noteEntity.copy(color = colors[randomColor].toArgb())
                    onSave.invoke(selectedNote)
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
fun FieldPreview() {
        CreateNoteUI(NoteEntity())

}