package com.learning.features.presentation.notes

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learning.features.data.local.NoteEntity


@Composable
fun NotesMainScreen(listOfNoteEntities: List<NoteEntity> = listOf(),
                    createNote:()-> Unit={},
                    onClickOfNote:(noteEntity: NoteEntity)->Unit={},
                    onBackPress:()-> Unit={}) {

    BackHandler {
        onBackPress.invoke()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                createNote.invoke()
            }) {
                Icon(imageVector = Icons.Default.Add,  contentDescription = "Create Notes Button")
            }
        },

    ) { innerPadding->
        Column(modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(innerPadding),
        ) {
            Text(text = "Notes",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                ),
                modifier = Modifier.padding(5.dp)
            )
            LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()){
                items(listOfNoteEntities){ notes->
                    NoteUI(notes){
                        onClickOfNote.invoke(it)
                    }
                }
            }
        }
    }

}


@Composable
fun NoteUI(noteEntity: NoteEntity = NoteEntity(), onClickOfNote: (noteEntity: NoteEntity) -> Unit) {

    ElevatedCard(
        modifier = Modifier.fillMaxSize()
            .padding(5.dp)
            .clip(RoundedCornerShape(15.dp))
//            .background(color= Color(note.color)) set this when
            .clickable{
                onClickOfNote.invoke(noteEntity)
            }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(5.dp)
        ) {
            Text(text = noteEntity.title,)
            HorizontalDivider(modifier = Modifier.height(5.dp))
            Text(text = noteEntity.description,)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewActivity() {
    NotesMainScreen()

}
