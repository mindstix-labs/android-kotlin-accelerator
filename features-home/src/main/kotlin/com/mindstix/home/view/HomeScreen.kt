import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen(
    userAnswers: Map<String, Any>,
    onTermsClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FormElements(
                text = "Welcome!",
                fontSize = 36.sp,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            if (userAnswers.isNotEmpty()) {
                FormElements(
                    text = "Here’s a summary of your input:",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            userAnswers.forEach { (question, answer) ->
                AnswerRow(question, answer.toString())
                Spacer(modifier = Modifier.height(20.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            TextButton(
                onClick = onTermsClicked,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .shadow(10.dp)  // Shadow effect
            ) {
                FormElements(
                    text = "Read Terms and Conditions",
                    fontSize = 18.sp,
                )
            }
        }
    }
}

@Composable
fun AnswerRow(question: String, answer: String) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.List, // Placeholder icon, replace with actual icon id
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            FormElements(
                text = question,
                fontSize = 18.sp
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowForward, // Placeholder icon, replace with actual icon id
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            FormElements(
                text = answer,
                fontSize = 18.sp,
            )
        }
    }
}

@Composable
fun FormElements(
    text: String,
    fontSize: TextUnit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Future enhancement: draw a glow effect for neon text
        }
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = modifier
        )
    }
}
