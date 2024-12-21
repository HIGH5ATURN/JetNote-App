package com.example.jetnote.screen
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.jetnote.R
import com.example.jetnote.components.NoteButton
import com.example.jetnote.components.NoteInputText
import com.example.jetnote.components.NoteRow
import com.example.jetnote.model.Note


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit,

){

    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    Column(modifier = Modifier
        //.fillMaxSize()
        .padding(6.dp)) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
                    },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications,
                    contentDescription = "App Icon" )
            },
            colors = topAppBarColors(
                containerColor = Color(0xFFDADFE3),
            )
        )
        //Content
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {


            NoteInputText(
                modifier = Modifier
                    .padding(top = 9.dp,
                    bottom = 8.dp),
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it
                },
                )

            NoteInputText(
                modifier = Modifier
                    .padding(top = 9.dp,
                        bottom = 8.dp),
                text = description,
                label = "Add a note",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                },
            )

            Spacer(modifier = Modifier.padding(10.dp))

            NoteButton(
                text = "Save",
                onClick = {
                    if(title.isNotEmpty() && description.isNotEmpty()){
                        //save / add to list
                        onAddNote(Note(title = title,
                            description = description))

                        title = ""
                        description = ""
                        Toast.makeText(context, "Note Added",Toast.LENGTH_SHORT).show()
                    }
                },

            )


        }
        HorizontalDivider(modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(notes) { note ->

//                Text(text = note.title)
                NoteRow(
                    note = note,
                    onNoteClicked = {
                        onRemoveNote(note)
                    }
                )
            }
        }

    }
}



//@Preview(showBackground = true)
//@Composable
//fun NoteScreenPreview(){
//    NoteScreen()
//}