
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import interfaces.PlatformParams
import interfaces.ScreenType
import interfaces.VerticalScaffold
import models.ViewModelData
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.AppTheme
import ui.connectionMenu.MenuCustomAnimation

@Composable
@Preview
fun App() {
    val vmData = ViewModelData()
    AppTheme {
        if (PlatformParams.screenType.value == ScreenType.HORIZONTAL)
            Scaffold(
                topBar = { TopAppBarImpl(vmData) }
            ) {
                Row {
                    MenuCustomAnimation(vmData)
                    FrontLayerContent(vmData)
                }
            }
        else
            VerticalScaffold(vmData)
    }
//    vmData.refresh()
}

@Composable
fun TopAppBarImpl(vmData: ViewModelData) {
    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        IconButton(onClick = { vmData.menuVisibility.value = !vmData.menuVisibility.value }) {
            Icon(Icons.Default.Menu, null)
        }
        Text(
            text = "Stand control",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        IconButton(onClick = {}) {
            Icon(Icons.Default.Settings, null)
        }
    }
}

@Composable
fun FrontLayerContent(vmData: ViewModelData) {

}