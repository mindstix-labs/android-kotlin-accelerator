import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SkinToneSlider(onColorSelected: (String) -> Unit) {
    var sliderPosition by remember { mutableStateOf(0f) }
    val skinTones =
        listOf(
            SkinTone(Color(0xFFFFE0BD), "Very Fair"),
            SkinTone(Color(0xFFF8E6D3), "Fair"),
            SkinTone(Color(0xFFE0AC69), "Light"),
            SkinTone(Color(0xFFD8B295), "Light Brown"),
            SkinTone(Color(0xFFA67C52), "Brown"),
            SkinTone(Color(0xFF8B5E3C), "Dark Brown"),
            SkinTone(Color(0xFF5A3821), "Dark"),
        )

    val selectedSkinTone = interpolateSkinTone(skinTones, sliderPosition)

    Box(
        modifier = Modifier
            .background(
                color = Color.LightGray, // You can change this to any background color
                shape = RoundedCornerShape(16.dp) // Adjust the corner radius here
            )
            .padding(16.dp) // Inner padding for the content inside the box
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = selectedSkinTone.name,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(top = 8.dp)
            )
            Row {
                Slider(
                    modifier = Modifier.weight(1f),
                    value = sliderPosition,
                    onValueChange = {
                        sliderPosition = it
                        onColorSelected(selectedSkinTone.name)
                    },
                    valueRange = 0f..1f,
                    colors = SliderDefaults.colors(
                        thumbColor = Color.White,
                        activeTrackColor = selectedSkinTone.color.copy(alpha = 0.5f)
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(selectedSkinTone.color, shape = CircleShape)
                )
            }
        }
    }

}

data class SkinTone(val color: Color, val name: String)

fun interpolateSkinTone(
    skinTones: List<SkinTone>,
    position: Float,
): SkinTone {
    val scaledPosition = position * (skinTones.size - 1)
    val index = scaledPosition.toInt()
    val fraction = scaledPosition - index
    val startSkinTone = skinTones[index]
    val endSkinTone = skinTones[minOf(index + 1, skinTones.size - 1)]
    val blendedColor = blendColors(startSkinTone.color, endSkinTone.color, fraction)
    return SkinTone(blendedColor, startSkinTone.name)
}

fun blendColors(
    startColor: Color,
    endColor: Color,
    fraction: Float,
): Color {
    val r = startColor.red + fraction * (endColor.red - startColor.red)
    val g = startColor.green + fraction * (endColor.green - startColor.green)
    val b = startColor.blue + fraction * (endColor.blue - startColor.blue)
    return Color(r, g, b)
}
