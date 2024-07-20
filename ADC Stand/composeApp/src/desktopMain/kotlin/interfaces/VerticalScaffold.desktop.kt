package interfaces

import FrontLayerContent
import TopAppBarImpl
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import models.ViewModelData
import ui.connectionMenu.MenuCustomAnimation

@Composable
actual fun VerticalScaffold(vmData: ViewModelData) {
    Scaffold(
        topBar = { TopAppBarImpl(vmData) }
    ) {
        Row {
            MenuCustomAnimation(vmData)
            FrontLayerContent(vmData)
        }
    }
}