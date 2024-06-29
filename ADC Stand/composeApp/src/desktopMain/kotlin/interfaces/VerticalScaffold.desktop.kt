package interfaces

import FrontLayerContent
import TopAppBarImpl
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import ui.connectionMenu.MenuCustomAnimation

@Composable
actual fun VerticalScaffold(menuVisibility: MutableState<Boolean>) {
    Scaffold(
        topBar = { TopAppBarImpl(menuVisibility) }
    ) {
        Row {
            MenuCustomAnimation()
            FrontLayerContent()
        }
    }
}