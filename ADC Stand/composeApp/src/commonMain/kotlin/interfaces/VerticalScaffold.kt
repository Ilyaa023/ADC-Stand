package interfaces

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import models.Stand

@Composable
expect fun VerticalScaffold(menuVisibility: MutableState<Boolean>, stands: List<Stand>)