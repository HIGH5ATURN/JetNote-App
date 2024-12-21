package com.example.jetnote.components
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jetnote.model.Note
import java.text.SimpleDateFormat


@Composable
fun NoteInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
    ){
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
        ),
     maxLines = maxLine,
        label={ Text(text=label) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = androidx.compose.ui.text.input.ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone ={
            onImeAction()
            keyboardController?.hide()

        } ),
        modifier = Modifier
    )
}

@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
){
    
    Button(
        onClick = onClick,
        shape = CircleShape,
        enabled = enabled,
        modifier = Modifier
    ) {
        Text(text = text)
    }
    
}

@SuppressLint("SimpleDateFormat", "SuspiciousIndentation")
@Composable
fun NoteRow(
    modifier: Modifier=Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
){

    val entryDate = note.entryDate
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    val formattedDate = formatter.format(entryDate)
      Surface(modifier
          .padding(4.dp)
          .fillMaxWidth()
          .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp)),
          color = Color(0xFFA1D3F1),
          shadowElevation = 6.dp
      ) {
        Column(modifier
            .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {

            Row(modifier.fillMaxWidth()){
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier.padding(10.dp))
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "Delete Icon",
                    modifier.clickable {
                        onNoteClicked(note)
                    }
                )
            }

            Text(
                text = note.description,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text =formattedDate,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )

        }
      }
}