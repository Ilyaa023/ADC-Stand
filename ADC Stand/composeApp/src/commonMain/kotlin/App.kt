
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import interfaces.PlatformParams
import interfaces.ScreenType
import interfaces.VerticalScaffold
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.AppTheme
import ui.connectionMenu.MenuCustomAnimation

@Composable
@Preview
fun App() {
    var menuItem by remember { mutableStateOf(0) }
    var menuVisibility = mutableStateOf(true)
    AppTheme() {
        if (PlatformParams.screenType.value == ScreenType.HORIZONTAL)
            Scaffold(
                topBar = { TopAppBarImpl(menuVisibility) }
            ) {
                Row {
                    MenuCustomAnimation()
                    FrontLayerContent()
                }
            }
        else
            VerticalScaffold(menuVisibility)
    }
}

@Composable
fun TopAppBarImpl(menuVisibility: MutableState<Boolean>) {
    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        IconButton(onClick = { menuVisibility.value = !menuVisibility.value }) {
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
fun FrontLayerContent() {

}