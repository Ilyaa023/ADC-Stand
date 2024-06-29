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
