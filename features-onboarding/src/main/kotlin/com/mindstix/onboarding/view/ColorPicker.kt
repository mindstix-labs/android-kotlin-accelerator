import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RainbowColorSlider(
    onColorSelected: (String) -> Unit
) {
    var hue by remember { mutableFloatStateOf(0f) }

    val color = getColorForHue(hue)
    val colorName = getColorNameForHue(hue)

    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = hue,
            onValueChange = {
                hue = it
                onColorSelected(getColorNameForHue(hue))
            },
            valueRange = 0f..360f,
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = color.copy(alpha = 0.5f)
            )
        )

        Box(
            modifier = Modifier
                .size(60.dp)
                .background(color, shape = CircleShape)
                .border(
                    5.dp,
                    Color.White,
                    shape = CircleShape
                )
        )

        // Display the color name
        Text(text = colorName, color = Color.White, modifier = Modifier.padding(top = 8.dp))
    }
}
fun getColorNameForHue(hue: Float): String {
    return when (hue) {
        0f -> "Black"
        360f -> "White"
        in 0f..10f -> "Red"
        in 10f..20f -> "Deep Orange"
        in 20f..30f -> "Orange"
        in 30f..40f -> "Yellow Orange"
        in 40f..50f -> "Amber"
        in 50f..60f -> "Yellow"
        in 60f..70f -> "Lime"
        in 70f..80f -> "Lime Green"
        in 80f..90f -> "Green Yellow"
        in 90f..120f -> "Green"
        in 120f..140f -> "Spring Green"
        in 140f..160f -> "Mint Green"
        in 160f..180f -> "Cyan"
        in 180f..200f -> "Sky Blue"
        in 200f..220f -> "Azure"
        in 220f..240f -> "Blue"
        in 240f..260f -> "Indigo"
        in 260f..280f -> "Violet"
        in 280f..300f -> "Purple"
        in 300f..320f -> "Magenta"
        in 320f..340f -> "Pink"
        in 340f..350f -> "Hot Pink"
        in 350f..360f -> "Red"
        else -> "Unknown Color"
    }
}


fun getColorForHue(hue: Float): Color {
    return when (hue) {
        0f -> Color.Black
        360f -> Color.White
        else -> Color.hsl(hue, 1f, 0.5f)
    }
}
