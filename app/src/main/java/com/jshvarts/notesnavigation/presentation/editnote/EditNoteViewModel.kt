package com.jshvarts.notesnavigation.presentation.editnote

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.jshvarts.notesnavigation.domain.Note
import com.jshvarts.notesnavigation.domain.NotesManager

class EditNoteViewModel : ViewModel() {
    private val currentNote = MutableLiveData<Note>()

    private val editStatus = MutableLiveData<Boolean>()

    val observableCurrentNote: LiveData<Note>
        get() = currentNote

    val observableEditStatus: LiveData<Boolean>
        get() = editStatus

    fun editNote(id: Int, noteText: String) {
        editStatus.value = try {
            NotesManager.editNote(id, noteText)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    fun initNote(id: Int) {
        currentNote.value = NotesManager.getNote(id)
    }
}