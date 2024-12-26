package com.example.jetnote.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnote.data.NotesDatSource
import com.example.jetnote.model.Note
import com.example.jetnote.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository): ViewModel(){

    //private var noteList = mutableStateListOf<Note>()

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init{

        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect{
                    listOfNotes ->
                    if(listOfNotes.isEmpty()){
                        _noteList.value = emptyList()
                    }else{
                        _noteList.value = listOfNotes
                    }

                }
        }
    //    noteList.addAll(NotesDatSource().loadNotes())
    }

     fun addNote(note:Note) = viewModelScope.launch { repository.addNote(note) }

     fun updateNote(note:Note) = viewModelScope.launch { repository.updateNote(note) }

     fun removeNote(note:Note) = viewModelScope.launch { repository.deleteNote(note) }


}