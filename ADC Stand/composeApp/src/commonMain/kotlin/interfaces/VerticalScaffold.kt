package interfaces

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
expect fun VerticalScaffold(menuVisibility: MutableState<Boolean>)