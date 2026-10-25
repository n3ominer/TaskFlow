package fr.esgi.taskflow.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val LightColors = lightColorScheme(
    primary = Color(0xFF6257A8),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE9E4FF),
    onPrimaryContainer = Color(0xFF2E255C),
    secondary = Color(0xFF317D70),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFDDF3EA),
    onSecondaryContainer = Color(0xFF18483E),
    background = Color(0xFFF7F8FC),
    onBackground = Color(0xFF1D2540),
    surface = Color.White,
    onSurface = Color(0xFF1D2540),
    surfaceVariant = Color(0xFFF0F1F8),
    onSurfaceVariant = Color(0xFF59617A),
    outline = Color(0xFFD7DBE7),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFC5BCFF),
    onPrimary = Color(0xFF32286C),
    primaryContainer = Color(0xFF4A3E89),
    onPrimaryContainer = Color(0xFFE9E4FF),
    secondary = Color(0xFF96DDC9),
    onSecondary = Color(0xFF123E33),
    secondaryContainer = Color(0xFF285B4E),
    onSecondaryContainer = Color(0xFFDDF3EA),
    background = Color(0xFF151925),
    onBackground = Color(0xFFF0F1FA),
    surface = Color(0xFF202536),
    onSurface = Color(0xFFF0F1FA),
    surfaceVariant = Color(0xFF30364A),
    onSurfaceVariant = Color(0xFFBEC4D7),
    outline = Color(0xFF596178),
)

private val TaskFlowShapes = Shapes(
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(18.dp),
    large = RoundedCornerShape(28.dp),
)

private val TaskFlowTypography = Typography(
    headlineLarge = TextStyle(fontSize = 34.sp, lineHeight = 40.sp, fontWeight = FontWeight.Bold),
    headlineMedium = TextStyle(fontSize = 28.sp, lineHeight = 34.sp, fontWeight = FontWeight.Bold),
    headlineSmall = TextStyle(fontSize = 24.sp, lineHeight = 30.sp, fontWeight = FontWeight.Bold),
    titleLarge = TextStyle(fontSize = 20.sp, lineHeight = 26.sp, fontWeight = FontWeight.SemiBold),
    titleMedium = TextStyle(fontSize = 16.sp, lineHeight = 22.sp, fontWeight = FontWeight.SemiBold),
    bodyLarge = TextStyle(fontSize = 16.sp, lineHeight = 24.sp),
    bodyMedium = TextStyle(fontSize = 14.sp, lineHeight = 20.sp),
    labelLarge = TextStyle(fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.SemiBold),
)

@Composable
fun TaskFlowTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) DarkColors else LightColors,
        typography = TaskFlowTypography,
        shapes = TaskFlowShapes,
        content = content,
    )
}
