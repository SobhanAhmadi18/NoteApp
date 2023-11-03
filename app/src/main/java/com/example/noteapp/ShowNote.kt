import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noteapp.Note
// Code from ChatGpt
@Composable
fun ShowNote(note: Note, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = note.title, fontWeight = FontWeight.Bold, fontSize = 25.sp)
        Text(text = note.body, fontSize = 20.sp)
        Button(onClick = {
            navController.navigate("showNote/{noteId}")
        }) {
            Text(text = "Back")
        }
    }
}