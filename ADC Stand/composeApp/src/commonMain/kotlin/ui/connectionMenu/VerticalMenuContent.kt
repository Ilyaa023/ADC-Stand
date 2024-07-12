package ui.connectionMenu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import models.Stand

@Composable
fun MenuCustomAnimation(menuVisibility: MutableState<Boolean>, stands: List<Stand>){
    var width by remember { mutableStateOf(300.dp) }
    AnimatedVisibility(visible = menuVisibility.value,
                       enter = fadeIn() + slideInHorizontally(),
                       exit = slideOutHorizontally() + fadeOut(),) {
        Box(
            modifier = Modifier.padding(0.dp, 55.dp, 0.dp, 0.dp).width(width)
                .background(MaterialTheme.colorScheme.secondaryContainer)
        ) {
            VerticalMenuContent(Modifier.padding(end = 10.dp), stands)
            Box(modifier = Modifier.fillMaxHeight().width(10.dp)
                .background(MaterialTheme.colorScheme.primaryContainer).align(Alignment.CenterEnd)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        width += dragAmount.x.toDp()
                        if (width < 300.dp) width = 300.dp
                        if (width > 500.dp) width = 500.dp
                    }
                })
        }
    }
}

@Composable
fun VerticalMenuContent(modifier: Modifier = Modifier, stands: List<Stand>){
//    var selectedStand by remember { mutableStateOf(-1) }
//    var stands by remember { mutableStateOf(mutableListOf<ConnectionCard>()) }
//    var updateList by remember { mutableStateOf(false) }
//    var standToRemove by remember { mutableStateOf(-1) }
//    var availableComPorts by remember { mutableStateOf(mutableListOf<String>()) }
    var isConnected by remember { mutableStateOf(false) }

//    Column(modifier = modifier.padding(20.dp)) {
//        Text(text = "Connections", color = MaterialTheme.colorScheme.onSecondaryContainer, fontSize = 20.sp)
//        Box (modifier = Modifier.height(10.dp).background(Color.Transparent))
//        ConnectionCard(listOfCOMPorts = stands,
//                       onRefreshClick = { checkerCommunication?.CheckStands { availableComPorts = it } },
//                       onSelectCOMPort = {},
//                       onCloseClick = {
//
//                       })
    LazyColumn(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
//            if (updateList) updateList = false
        itemsIndexed(stands){ index, item ->
            StandCard(stand = item,
                      onRemoveClick = {},
                      onStartClick = {})
//            ConnectionCard(listOfCOMPorts = availableComPorts,
//                                onRefreshClick = {
//                                    checkerCommunication?.CheckStands { availableComPorts = it }
//                                },
//                                onCloseClick = {
//                                    stands.removeAt(index)
//                                    if (selectedStand == index)
//                                            selectedStand = -1
//                                    updateList = true
//                                })
        }

        item {
            FilledIconButton(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                colors = IconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer),
                onClick = {
                    stands.toMutableList().add(Stand())
                }){
                Image(Icons.Default.Add, null)
            }
        }
    }
}


