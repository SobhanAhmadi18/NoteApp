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
// https://chat.openai.com/c/783db35a-867d-4332-9868-b4d8d202faac
// it contains things i have searched up for the project so you have to scroll up
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
            navController.navigateUp()
        }) {
            Text(text = "Back")
        }
    }
}