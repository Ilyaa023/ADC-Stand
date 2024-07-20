package interfaces

import androidx.compose.runtime.Composable
import models.ViewModelData

@Composable
expect fun VerticalScaffold(vmData: ViewModelData)