package com.learning.features.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.features.data.Repository
import com.learning.features.data.local.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class NotesViewModel constructor(
    val repository: Repository
) : ViewModel() {

    private var _listOfNotes: MutableStateFlow<List<NoteEntity>?> = MutableStateFlow(null)
    val listOfNotes = _listOfNotes.asStateFlow()

    private var _selectedNoteEntity: MutableStateFlow<NoteEntity> = MutableStateFlow(NoteEntity())
    val selectedNote = _selectedNoteEntity.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllNotes()
        }
    }


    fun updateSelectedNote(noteEntity:NoteEntity){
        _selectedNoteEntity.value = noteEntity
    }

    fun insertNote(noteEntity: NoteEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveOrUpdateNote(noteEntity)
        }
    }

    suspend fun deleteNote(noteEntity: NoteEntity){
        repository.deleteNote(noteEntity)
    }

    private suspend fun getAllNotes() {
        repository.getAllNotes().collect {
            _listOfNotes.value = it
        }
    }
}