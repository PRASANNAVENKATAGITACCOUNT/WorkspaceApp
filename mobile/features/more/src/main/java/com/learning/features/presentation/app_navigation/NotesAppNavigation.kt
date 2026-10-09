package com.learning.features.presentation.app_navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.learning.features.data.local.NoteEntity
import com.learning.features.presentation.notes.CreateNoteUI
import com.learning.features.presentation.notes.NotesMainScreen
import com.learning.features.presentation.notes.NotesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotesAppNavigation(navigateToMainScreen:()-> Unit={}) {
    val notesViewModel = koinViewModel<NotesViewModel>()
    val listOfNotes = notesViewModel.listOfNotes.collectAsState()
    val selectedNote = notesViewModel.selectedNote.collectAsState()
    val customNoteEntity = NoteEntity()

    val backStackList = rememberNavBackStack(
        elements = arrayOf<NavKey>(
            AppRoutes.NotesRoutes.NotesList
        )
    )

    NavDisplay(
        backStack = backStackList,
        entryProvider = entryProvider {
            entry<AppRoutes.NotesRoutes.NotesList> {
                NotesMainScreen(
                    listOfNoteEntities = listOfNotes.value ?: emptyList(),
                    createNote = {
                        backStackList.add(AppRoutes.NotesRoutes.NotesDetails(0))
                    },
                    onClickOfNote = { note ->
                        notesViewModel.updateSelectedNote(note)
                        backStackList.add(AppRoutes.NotesRoutes.NotesDetails(note.id))
                    },
                    onBackPress = {
                        navigateToMainScreen.invoke()
                    }
                )
            }

            entry<AppRoutes.NotesRoutes.NotesDetails> {
                CreateNoteUI(
                    noteEntity = selectedNote.value,
                    onTitleChange = { title ->
                        selectedNote.value.let {
                            val note = it
                            val newNote = note.copy(title = title)
                            Log.d("dmfkjnfnjng", "AppNavigation: $newNote")
                            notesViewModel.updateSelectedNote(newNote)
                        }
                    },
                    onDescriptionChange = { description ->
                        selectedNote.value.let {
                            val note = it
                            val newNote = note.copy(description = description)
                            notesViewModel.updateSelectedNote(newNote)
                        }
                    },
                    onSave =
                        { note ->
                            notesViewModel.insertNote(note)
                            notesViewModel.updateSelectedNote(customNoteEntity)
                            backStackList.removeLastOrNull()
                        },
                    onCancel = {
                        backStackList.removeLastOrNull()
                        notesViewModel.updateSelectedNote(customNoteEntity)
                    }
                )
            }
        }
    )

}

sealed class SCREEN(val path: String) {
    object HOME_SCREEN: SCREEN("HOME_SCREEN")
    object ADD_NOTE_SCREEN: SCREEN("ADD_NOTE_SCREEN")
}
