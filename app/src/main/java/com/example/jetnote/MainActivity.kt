package com.example.jetnote
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetnote.model.Note
import com.example.jetnote.screen.NoteScreen
import com.example.jetnote.screen.NoteViewModel
import com.example.jetnote.ui.theme.JetNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetNoteTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                ) {
                    ///when comes to data this viewmodel is the single source of truth
                    val noteViewModel: NoteViewModel by viewModels()

                    NotesApp(noteViewModel)

                }

            }
        }
    }
}

@Composable
fun NotesApp(noteViewModel: NoteViewModel= viewModel()){

    val notesList = noteViewModel.getAllNotes();
    NoteScreen(
        notes = notesList,
        onAddNote = {
          noteViewModel.addNote(it)
        },
        onRemoveNote = {
            noteViewModel.removeNote(it)
        },
    )
}

