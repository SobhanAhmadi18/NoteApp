import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.noteapp.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun editNote(note: SnapshotStateList<Note>, noteList: NavHostController, avController: NavController) {
    var editedTitle by remember { mutableStateOf(note.title) }
    var editedBody by remember { mutableStateOf(note.body) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Edit Note")
        TextField(
            value = editedTitle,
            onValueChange = { newTitle ->
                editedTitle = newTitle
            }
        )
        TextField(
            value = editedBody,
            onValueChange = { newBody ->
                editedBody = newBody
            }
        )
        Row {
            Button(onClick = {
                if (editedTitle.length < 50 && editedTitle.length > 3 && editedBody.length < 120) {
                    val editedNote = note.copy(title = editedTitle, body = editedBody)
                    val index = noteList.indexOf(note)
                    if (index != -1) {
                        noteList[index] = editedNote
                        navController.navigateUp()
                    }
                }
            }) {
                Text(text = "Save Changes")
            }

            Button(onClick = {
                navController.navigateUp()
            }) {
                Text(text = "Cancel")
            }
        }
    }
}
