import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import interfaces.MenuItem
import interfaces.PlatformParams
import interfaces.ScreenType
import interfaces.drawHorizontalMenu
import interfaces.drawVerticalMenu
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.AppTheme
import ui.pages.ADCPage
import ui.pages.ConnectionPage
import ui.pages.KnowledgesPage
import ui.pages.SettingsPage
import ui.pages.StartPage

@Composable
@Preview
fun App() {
    var menuItem by remember { mutableStateOf(MenuItem.START_PAGE) }
    AppTheme() {
        if (PlatformParams.screenType.value == ScreenType.VERTICAL)
            Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
                drawHorizontalMenu{ menuItem = it }
                MenuContent(menuItem)
            }
        else
            Row(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
                drawVerticalMenu{ menuItem = it }
                MenuContent(menuItem)
            }

    }
//            Button(onClick = {
//                showContent = !showContent
//            }) {
//                Text(stringResource(Res.string))
//            }
//            AnimatedVisibility(showContent) {
////                ports = appInitialData.getPorts()
//
//                val greeting = remember { Greeting().greet() }
//                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//
//                    Image(painterResource(Res.drawable.compose_multiplatform), null)
//                    Text("Compose: $greeting")
//                }
//            }
//        }
//    }
}

@Composable
fun MenuContent(menuItem: MenuItem){
    when (menuItem) {
        MenuItem.START_PAGE -> StartPage()
        MenuItem.CONNECTION_PAGE -> ConnectionPage()
        MenuItem.SETTINGS_PAGE -> SettingsPage()
        MenuItem.ADC_PAGE -> ADCPage()
        MenuItem.KNOWLEDGE_PAGE -> KnowledgesPage()
    }
}