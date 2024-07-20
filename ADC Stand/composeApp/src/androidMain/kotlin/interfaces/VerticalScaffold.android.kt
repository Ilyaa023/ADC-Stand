package interfaces

import FrontLayerContent
import TopAppBarImpl
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import models.ViewModelData
import ui.connectionMenu.VerticalMenuContent

@OptIn(ExperimentalMaterialApi::class)
@Composable
actual fun VerticalScaffold(vmData: ViewModelData) {
    BackdropScaffold(
        appBar = { TopAppBarImpl(vmData) },
        backLayerContent = { VerticalMenuContent(vmData = vmData) },
        frontLayerContent = { FrontLayerContent(vmData) },
        backLayerBackgroundColor = MaterialTheme.colorScheme.secondaryContainer
    )
}
