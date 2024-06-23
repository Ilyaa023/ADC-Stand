package adc.stand.viewer

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import interfaces.PlatformParams
import interfaces.ScreenType

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val height = LocalConfiguration.current.screenHeightDp
            val width = LocalConfiguration.current.screenWidthDp
            PlatformParams.screenType.value =
                if (height > width) ScreenType.VERTICAL else ScreenType.HORIZONTAL
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}

@Composable
fun VerticalMenu(onChanged: (Int) -> Unit){
    Row {
        Column (Modifier.fillMaxHeight()
                    .width(60.dp)
                    .background(MaterialTheme.colorScheme.surfaceContainer),
                verticalArrangement = Arrangement.Center) {
            Divider(color = MaterialTheme.colorScheme.surfaceContainerLow)
            MenuButton("Connect", Icons.Default.Add) { onChanged(1) }
            Divider(color = MaterialTheme.colorScheme.surfaceContainerLow)
            MenuButton("ADC", Icons.Default.Build) { onChanged(2) }
            Divider(color = MaterialTheme.colorScheme.surfaceContainerLow)
            MenuButton("Settings", Icons.Default.Settings) { onChanged(3) }
            Divider(color = MaterialTheme.colorScheme.surfaceContainerLow)
        }
    }

}

@Composable
fun HorizontalMenu(onChanged: (Int) -> Unit){
    Row (Modifier.fillMaxWidth()
             .height(60.dp)
             .background(MaterialTheme.colorScheme.surfaceContainer),
         horizontalArrangement = Arrangement.Center,
         verticalAlignment = Alignment.Top) {
        VerticalDivider()
        MenuButton("Connect", Icons.Default.Add) { onChanged(1) }
        VerticalDivider()
        MenuButton("ADC", Icons.Default.Build) { onChanged(2) }
        VerticalDivider()
        MenuButton("Settings", Icons.Default.Settings) { onChanged(3) }
        VerticalDivider()
    }
}

@Composable
fun MenuButton(text: String, image: ImageVector,onClick: () -> Unit){
    Button(modifier = Modifier.height(60.dp).width(60.dp),
           contentPadding = PaddingValues(0.dp),
           shape = RectangleShape,
           colors = ButtonDefaults.buttonColors(
               backgroundColor = MaterialTheme.colorScheme.surfaceContainer,
               contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
               disabledBackgroundColor = MaterialTheme.colorScheme.surfaceContainer,
               disabledContentColor = MaterialTheme.colorScheme.surfaceContainerLow
           ),
           elevation = ButtonDefaults.elevation(0.dp),
           onClick = onClick){
        Column (modifier = Modifier.fillMaxWidth().height(60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
            Icon(image,
                 contentDescription = null)
            Text(text, fontSize = 11.sp)
        }
    }
}

@Composable
fun VerticalDivider(modifier: Modifier = Modifier,
                    color: Color = MaterialTheme.colorScheme.surfaceContainerLow,
                    thickness: Dp = 1.dp) {
    Box(modifier.fillMaxHeight().width(thickness).background(color))
}