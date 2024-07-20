
import androidx.compose.ui.geometry.Offset
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
import kotlin.math.PI
import kotlin.math.sin

fun main() = application {
//    ConnectionWork().Refresh { list, loaded ->
//        println("list: $list \n\t$loaded")
//    }
    val state = rememberWindowState()
    state.placement = WindowPlacement.Floating
    Window(
        onCloseRequest = ::exitApplication,
        state = state,
        title = "ADC Stand",
        icon = AppIcon,
//        undecorated = true
    ) {
        PlatformParams.screenType.value =
            if (state.size.height > state.size.width) ScreenType.VERTICAL else ScreenType.HORIZONTAL
        App()
    }
}

object AppIcon : Painter() {
    override val intrinsicSize = Size(256f, 256f)
    override fun DrawScope.onDraw() {
        drawRect(Color(0xFFFFE5C1))
        for (i in 0..8){
            drawLine(
                start = Offset(x = intrinsicSize.width * (0.03125f - 0.00378f * i),
                               y = intrinsicSize.height * (0.03125f + 0.03125f * sin(PI*i/8).toFloat())),
                end = Offset(x = intrinsicSize.width * (0.03125f - 0.00378f * (i + 1)),
                             y = intrinsicSize.height * (0.03125f + 0.03125f * sin(PI*(i + 1) / 8).toFloat())),
                color = Color.Blue
            )
        }
        for (i in 0..8){
            drawLine(
                start = Offset(x = intrinsicSize.width * (0.03125f + 0.00378f * i),
                               y = intrinsicSize.height * (0.03125f - 0.03125f * sin(PI * i / 8).toFloat())),
                end = Offset(x = intrinsicSize.width * (0.03125f + 0.00378f * (i + 1)),
                             y = intrinsicSize.height * (0.03125f - 0.03125f * sin(PI * i / 8).toFloat())),
                color = Color.Blue
            )
        }
    }
}

