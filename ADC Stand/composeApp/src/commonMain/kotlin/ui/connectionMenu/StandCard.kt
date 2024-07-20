package ui.connectionMenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import models.Stand
import useCases.ConnectionWork
@Composable
fun StandCard(
        stand: Stand,
        onStartClick: () -> Unit = { println("start click not implemented") },
        onConnectClick: () -> Unit = { ConnectionWork().Connect(stand) },
        onDisconnectClick: () -> Unit = { ConnectionWork().Disconnect(stand) }
){
    var isConnected by remember { stand.isConnected }
//    var connectionType by remember { stand.selectedCT }

    var backgroundColor = MaterialTheme.colorScheme.surfaceBright
    var onBackgroundColor = MaterialTheme.colorScheme.onSurface
    var activatedColor = MaterialTheme.colorScheme.tertiary
    var onActivatedColor = MaterialTheme.colorScheme.onTertiary
    if (!isConnected){
        backgroundColor = MaterialTheme.colorScheme.outlineVariant
        activatedColor = MaterialTheme.colorScheme.secondary
        onActivatedColor = MaterialTheme.colorScheme.onSecondary
    }

    Card (colors = CardColors(contentColor = onBackgroundColor,
                              containerColor = backgroundColor,
                              disabledContainerColor = backgroundColor,
                              disabledContentColor = onBackgroundColor),
          modifier = Modifier.fillMaxWidth().padding(10.dp)) {
        if (stand.standType == null)
            Text("Imposter stand", color = onBackgroundColor)
        else
            Text(stand.standType!!.name, color = onBackgroundColor)
        Text(stand.connection.connectionName, color = onBackgroundColor)
        Row {
            FilledIconButton(onClick = onConnectClick){
                Image(Icons.Default.Done, null)
            }
            FilledIconButton(onClick = onDisconnectClick){
                Image(Icons.Default.Close, null)
            }
            FilledIconButton(onClick = onStartClick){
                Image(Icons.Default.PlayArrow, null)
            }
        }
    }
}
//        Row(modifier = Modifier.fillMaxWidth().padding(10.dp)){
//            Column(modifier = Modifier.weight(3f)) {
//                Row {
//                    ConnectionType.entries.forEach {
//                        val isActive = connectionType == it
//                        FilledIconButton(
//                            enabled = !isConnected,
//                            onClick = { connectionType = it },
//                            modifier = Modifier.weight(1f),
//                            shape = RectangleShape,
//                            colors = IconButtonColors(
//                                containerColor = if (isActive) activatedColor else backgroundColor,
//                                contentColor = if (isActive) onActivatedColor else onBackgroundColor,
//                                disabledContainerColor = if (isActive) activatedColor else backgroundColor,
//                                disabledContentColor = if (isActive) onActivatedColor else onBackgroundColor)){
//                            Text(it.name, color = if (isActive) onActivatedColor else onBackgroundColor)
//                        }
//                    }
//                }
//                LazyColumn(modifier = Modifier.height(80.dp)) {
//                    availableConnections.forEach {
//                        val isActive = selectedConnection == it
//                        if (connectionType == it.connectionType)
//                            item {
//                                FilledIconButton(
//                                    enabled = !isConnected,
//                                    onClick = { selectedConnection = it },
//                                    shape = RectangleShape,
//                                    colors = IconButtonColors(
//                                        containerColor = if (isActive) activatedColor else backgroundColor,
//                                        contentColor = if (isActive) onActivatedColor else onBackgroundColor,
//                                        disabledContainerColor = if (isActive) activatedColor else backgroundColor,
//                                        disabledContentColor = if (isActive) onActivatedColor else onBackgroundColor)){
//                                    Text(it.connectionName, color = if (isActive) onActivatedColor else onBackgroundColor)
//                                }
//                            }
//                    }
//                    if (availableConnections.isEmpty())
//                        item {
//                            Text(text = noCom, color = onBackgroundColor)
//                        }
//                }
//                Row {
//                    OutlinedButton(
//                        enabled = isConnected,
//                        onClick = onDisconnectClick,
//                        shape = RectangleShape,
//                        border = BorderStroke(1.dp, activatedColor),
//                        modifier = Modifier.weight(1f),
//                        colors = ButtonColors(
//                            containerColor = backgroundColor,
//                            contentColor = onBackgroundColor,
//                            disabledContainerColor = backgroundColor,
//                            disabledContentColor = onBackgroundColor)
//                    ) {
//                        Image(Icons.Default.Close, null)
//                    }
//                    FilledIconButton(
//                        enabled = !isConnected,
//                        onClick = onConnectClick,
//                        shape = RectangleShape,
//                        modifier = Modifier.weight(1f),
//                        colors = IconButtonColors(
//                            containerColor = activatedColor,
//                            contentColor = onActivatedColor,
//                            disabledContainerColor = activatedColor,
//                            disabledContentColor = onActivatedColor)){
//                        Image(Icons.Default.Done, null)
//                    }
//                }
//            }
//            Column(modifier = Modifier.width(60.dp), horizontalAlignment = Alignment.CenterHorizontally) {
//                IconButton(
//                    onClick = {
//                        onDisconnectClick()
//                        onRemoveClick()
//                    },
//                    colors = IconButtonColors(
//                        containerColor = backgroundColor, contentColor = onBackgroundColor,
//                        disabledContainerColor = backgroundColor, disabledContentColor = onBackgroundColor)
//                ){
//                    Image(Icons.Default.Close, null)
//                }
//                IconButton(
//                    enabled = !isConnected,
//                    onClick = onRefreshClick,
//                    colors = IconButtonColors(
//                        containerColor = backgroundColor, contentColor = onBackgroundColor,
//                        disabledContainerColor = backgroundColor, disabledContentColor = onBackgroundColor)
//                ){
//                    Image(Icons.Default.Refresh, null)
//                }
//                IconButton(
//                    enabled = isConnected,
//                    onClick = onStartClick,
//                    colors = IconButtonColors(
//                        containerColor = backgroundColor, contentColor = onBackgroundColor,
//                        disabledContainerColor = backgroundColor, disabledContentColor = onBackgroundColor)
//                ){
//                    Image(Icons.Default.PlayArrow, null)
//                }
//            }
//        }
//    }



//@Preview
//@Composable
//fun StandCard(listOfConnections: MutableList<IConnectionDataflow>,
////                   selectedCard: Boolean = false,
//                   selectedCard: Boolean = false,
//                   onRefreshClick: () -> Unit,
//                   onSelectConnection: (IConnectionDataflow) -> Unit,
//                   onCloseClick: () -> Unit){
//    val noCom = "No available COM ports"
//    val communicationType = listOf("COM", "BT", "Wi-Fi")
//    val (selectedOption, onOptionSelected) = remember { mutableStateOf(communicationType[0]) }
//    var selectedConnection by remember { mutableStateOf<IConnectionDataflow?>(null) }
////    var selectedCard by remember { mutableStateOf(false) }
//
//    var backgroundColor = MaterialTheme.colorScheme.surfaceBright
//    var onBackgroundColor = MaterialTheme.colorScheme.onSurface
//    var activatedColor = MaterialTheme.colorScheme.tertiary
//    var onActivatedColor = MaterialTheme.colorScheme.onTertiary
//    if (!selectedCard){
//        backgroundColor = MaterialTheme.colorScheme.outlineVariant
//        activatedColor = MaterialTheme.colorScheme.secondary
//        onActivatedColor = MaterialTheme.colorScheme.onSecondary
//    }
//
//    Card(colors = CardColors(contentColor = onBackgroundColor,
//                             containerColor = backgroundColor,
//                             disabledContainerColor = backgroundColor,
//                             disabledContentColor = onBackgroundColor),
//         modifier = Modifier.fillMaxWidth().padding(0.dp, 0.dp, 0.dp, 10.dp)) {
//        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
//            FilledIconButton(onClick = onCloseClick,
//                             colors = IconButtonColors(
//                                 containerColor = backgroundColor,
//                                 contentColor = onBackgroundColor,
//                                 disabledContainerColor = backgroundColor,
//                                 disabledContentColor = onBackgroundColor),
//                             shape = CircleShape,
//                             modifier = Modifier.size(40.dp)){
//                Image(imageVector = Icons.Default.Close,
//                      contentDescription = null,
//                      colorFilter = ColorFilter.tint(color = activatedColor))
//            }
//        }
//        Row(modifier = Modifier.selectableGroup()) {
//            communicationType.forEach { text ->
//                Row(verticalAlignment = Alignment.CenterVertically)
//                {
//                    RadioButton(
//                        selected = (text == selectedOption),
//                        onClick = { onOptionSelected(text) },
//                        colors = RadioButtonDefaults.colors(
//                            selectedColor = activatedColor,
//                            unselectedColor = onBackgroundColor,
//                        )
//                    )
//                    Text( text = text, fontSize = 15.sp,
//                          color = if (text == selectedOption) activatedColor else onBackgroundColor)
//                }
//            }
//        }
//        Row(modifier = Modifier.padding(start = 10.dp, bottom = 10.dp, end = 10.dp)) {
//            when (selectedOption){
//                communicationType[0] -> {
//                    LazyColumn (
//                        modifier = Modifier.background(color = backgroundColor,
//                                                       shape = RoundedCornerShape(5.dp)
//                        )
//                            .height(70.dp).weight(2f)) {
//                        if (listOfConnections.isEmpty())
//                            item {
//                                Text(text = noCom, color = onBackgroundColor)
//                            }
//                        else
//                            for (connection in listOfConnections)
//                                item {
//                                    TextButton(onClick = { selectedConnection = connection },
//                                               colors = ButtonDefaults.buttonColors(
//                                                   backgroundColor = if ( selectedConnection == connection) activatedColor
//                                                   else backgroundColor,
//                                                   contentColor = if (selectedConnection == connection) onActivatedColor
//                                                   else onBackgroundColor
//                                               )){
//                                        Text(text = connection.connectionName,
//                                             color = if (selectedConnection == connection) onActivatedColor
//                                             else onBackgroundColor,
//                                             modifier = Modifier.width(150.dp))
//                                    }
//                                }
//                    }
//                }
//                else -> {
//                    Text (text = "Not yet implemented", color = onBackgroundColor)
//                }
//            }
//            Column (modifier = Modifier.weight(1f).padding(start = 20.dp),
//                    horizontalAlignment = Alignment.End) {
//                FilledIconButton(onClick = onRefreshClick,
//                                 colors = IconButtonColors(
//                                     containerColor = backgroundColor,
//                                     contentColor = onBackgroundColor,
//                                     disabledContainerColor = backgroundColor,
//                                     disabledContentColor = onBackgroundColor),
//                                 shape = CircleShape,
//                                 modifier = Modifier.size(40.dp)
//                ){
//                    Image(imageVector = Icons.Default.Refresh,
//                          contentDescription = null,
//                          colorFilter = ColorFilter.tint(color = onBackgroundColor))
//                }
//                FilledIconButton(onClick = {
//                    if (selectedOption == communicationType[0]) {
//                        if (selectedCard) onCloseClick() else onSelectCOMPort(selectedCOMPort)
//                    }
//                },
//                                 colors = IconButtonColors(
//                                     containerColor = activatedColor,
//                                     contentColor = onActivatedColor,
//                                     disabledContainerColor = activatedColor,
//                                     disabledContentColor = onActivatedColor),
//                                 shape = CircleShape,
//                                 modifier = Modifier.size(40.dp)
//                ){
//                    Image(imageVector = if (selectedCard) Icons.Default.Close else Icons.Default.Done,
//                          contentDescription = null,
//                          colorFilter = ColorFilter.tint(color = onActivatedColor))
//                }
//            }
//        }
//    }
//    class LocalListener: IConnectListener{
//        override fun callback(connectionName: String, outputMessage: String, success: Boolean) {
//            if (success){
//                selectedCard = true
//
//            }
//        }
//    }
//}
