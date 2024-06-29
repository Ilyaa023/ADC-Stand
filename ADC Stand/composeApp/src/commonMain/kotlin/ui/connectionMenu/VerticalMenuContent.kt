package ui.connectionMenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import interfaces.getCOMConnectionInstance

@Composable
fun MenuCustomAnimation(){
    var width by remember { mutableStateOf(300.dp) }
    Box (modifier = Modifier.padding(0.dp, 55.dp, 0.dp, 0.dp).width(width)
        .background(MaterialTheme.colorScheme.secondaryContainer)){
        VerticalMenuContent(Modifier.padding(end = 10.dp))
        Box(modifier = Modifier.fillMaxHeight().width(10.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .align(Alignment.CenterEnd)
            .pointerInput(Unit){
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    width += dragAmount.x.toDp()
                    if (width < 300.dp)
                        width = 300.dp
                    if (width > 700.dp)
                        width = 700.dp
                }
            })
    }
}

@Composable
fun VerticalMenuContent(modifier: Modifier = Modifier){
    var selectedStand by remember { mutableStateOf(0) }
    val checkerCommunication = getCOMConnectionInstance()
    var availableComPorts by remember { mutableStateOf(mutableListOf<String>()) }
    checkerCommunication?.CheckStands { availableComPorts = it }

    Column(modifier = modifier.padding(20.dp)) {
        Text(text = "Connections", color = MaterialTheme.colorScheme.onSecondaryContainer, fontSize = 20.sp)
        Box (modifier = Modifier.height(10.dp).background(Color.Transparent))
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            for (i in 0..4)
                item {
                    ConnectionCard(listOfCOMPorts = availableComPorts,
                                   selectedCard = selectedStand == i,
                                   onRefreshClick = {
                                       checkerCommunication?.CheckStands { availableComPorts = it }
                                   },
                                   onSelectClick = { selectedStand = i },
                                   onCloseClick = {})
                }
            item {
                FilledIconButton(
                    colors = IconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer),
                    onClick = {}){
                    Image(Icons.Default.Add, null)
                }
            }
        }
    }
}
