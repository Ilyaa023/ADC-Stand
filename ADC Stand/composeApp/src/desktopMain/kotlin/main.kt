import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import interfaces.PlatformParams
import interfaces.ScreenType

fun main() = application {

    var state = rememberWindowState()

    state.placement = WindowPlacement.Floating

    Window(
        onCloseRequest = ::exitApplication,
        state = state,
        title = "ADC Stand",
        icon = AppIcon

    ) {
        PlatformParams.screenType.value =
            if (state.size.height > state.size.width) ScreenType.VERTICAL else ScreenType.HORIZONTAL
        App()
    }
}

object AppIcon : Painter() {
    override val intrinsicSize = Size(256f, 256f)
    override fun DrawScope.onDraw() {
        drawOval(Color(0xFFFFE5C1))
    }
}

