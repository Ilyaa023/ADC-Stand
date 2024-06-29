package interfaces

import androidx.compose.runtime.mutableStateOf

enum class ScreenType {
    VERTICAL, HORIZONTAL
}

object PlatformParams {
    var screenType = mutableStateOf(ScreenType.VERTICAL)
    var visible = mutableStateOf(false)
}

