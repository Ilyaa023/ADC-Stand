package interfaces

import FrontLayerContent
import TopAppBarImpl
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import ui.connectionMenu.VerticalMenuContent

@OptIn(ExperimentalMaterialApi::class)
@Composable
actual fun VerticalScaffold(menuVisibility: MutableState<Boolean>) {
    var menuVisibility = mutableStateOf(true)
    BackdropScaffold(
        appBar = { TopAppBarImpl(menuVisibility) },
        backLayerContent = { VerticalMenuContent() },
        frontLayerContent = { FrontLayerContent() },
        backLayerBackgroundColor = MaterialTheme.colorScheme.secondaryContainer
    )
}
