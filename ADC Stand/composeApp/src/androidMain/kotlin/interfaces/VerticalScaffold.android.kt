package interfaces

import FrontLayerContent
import TopAppBarImpl
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import models.Stand
import ui.connectionMenu.VerticalMenuContent

@OptIn(ExperimentalMaterialApi::class)
@Composable
actual fun VerticalScaffold(menuVisibility: MutableState<Boolean>, stands: List<Stand>) {
    BackdropScaffold(
        appBar = { TopAppBarImpl(menuVisibility) },
        backLayerContent = { VerticalMenuContent() },
        frontLayerContent = { FrontLayerContent() },
        backLayerBackgroundColor = MaterialTheme.colorScheme.secondaryContainer
    )
}
