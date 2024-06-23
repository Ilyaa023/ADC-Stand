package interfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.MenuButton

@Composable
actual fun drawHorizontalMenu(onChange: (MenuItem) -> Unit) {
    Row (
        Modifier.fillMaxWidth()
             .height(60.dp)
             .background(MaterialTheme.colorScheme.surfaceContainer),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top) {
        VerticalDivider()
        MenuButton("Connect", Icons.Default.Add) { onChange(MenuItem.CONNECTION_PAGE) }
        VerticalDivider()
        MenuButton("ADC", Icons.Default.Build) { onChange(MenuItem.ADC_PAGE) }
        VerticalDivider()
        MenuButton("Settings", Icons.Default.Settings) { onChange(MenuItem.SETTINGS_PAGE) }
        VerticalDivider()
    }
}

@Composable
actual fun drawVerticalMenu(onChange: (MenuItem) -> Unit) {
    Column (Modifier.fillMaxHeight()
                .width(60.dp)
                .background(MaterialTheme.colorScheme.surfaceContainer),
            verticalArrangement = Arrangement.Center) {
        Divider(color = MaterialTheme.colorScheme.surfaceContainerLow)
        MenuButton("Connect", Icons.Default.Add) { onChange(MenuItem.CONNECTION_PAGE) }
        Divider(color = MaterialTheme.colorScheme.surfaceContainerLow)
        MenuButton("ADC", Icons.Default.Build) { onChange(MenuItem.ADC_PAGE) }
        Divider(color = MaterialTheme.colorScheme.surfaceContainerLow)
        MenuButton("Settings", Icons.Default.Settings) { onChange(MenuItem.SETTINGS_PAGE) }
        Divider(color = MaterialTheme.colorScheme.surfaceContainerLow)
    }
}