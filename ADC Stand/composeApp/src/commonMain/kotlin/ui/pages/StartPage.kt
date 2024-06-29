package ui.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import interfaces.PlatformParams.visible
import kotlin.concurrent.thread

@Composable
fun StartPage(){

    thread {
        Thread.sleep(1000)
        visible.value = true
    }

    AnimatedVisibility(
        visible = visible.value,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center) {
            Text(text = "Hello",
                 color = MaterialTheme.colorScheme.onBackground,
                 fontSize = 20.sp )
        }
    }

}