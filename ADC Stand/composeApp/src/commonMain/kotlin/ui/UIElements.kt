package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuButton(text: String, image: ImageVector, onClick: () -> Unit){
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