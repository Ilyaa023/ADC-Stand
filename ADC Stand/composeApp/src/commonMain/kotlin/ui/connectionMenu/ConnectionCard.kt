package ui.connectionMenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ConnectionCard(listOfCOMPorts: MutableList<String>,
                   selectedCard: Boolean = false,
                   onRefreshClick: () -> Unit,
                   onSelectClick: () -> Unit,
                   onCloseClick: () -> Unit){
    val noCom = "No available COM ports"
    val communicationType = listOf("COM", "BT", "Wi-Fi")
    var selectedCOMPort by remember { mutableStateOf(if (listOfCOMPorts.isNotEmpty()) listOfCOMPorts[0] else "") }
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(communicationType[0]) }

    var backgroundColor = MaterialTheme.colorScheme.outlineVariant
    var onBackgroundColor = MaterialTheme.colorScheme.onSurface
    var activatedColor = MaterialTheme.colorScheme.tertiary
    var onActivatedColor = MaterialTheme.colorScheme.onTertiary
    if (!selectedCard){
        backgroundColor = MaterialTheme.colorScheme.surfaceBright
        activatedColor = MaterialTheme.colorScheme.secondary
        onActivatedColor = MaterialTheme.colorScheme.onSecondary
    }

    Card(colors = CardColors(contentColor = onBackgroundColor,
                             containerColor = backgroundColor,
                             disabledContainerColor = backgroundColor,
                             disabledContentColor = onBackgroundColor),
         modifier = Modifier.fillMaxWidth().padding(0.dp, 0.dp, 0.dp, 10.dp)) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
            FilledIconButton(onClick = onCloseClick,
                             colors = IconButtonColors(
                                 containerColor = backgroundColor,
                                 contentColor = onBackgroundColor,
                                 disabledContainerColor = backgroundColor,
                                 disabledContentColor = onBackgroundColor),
                             shape = CircleShape,
                             modifier = Modifier.size(40.dp)){
                Image(imageVector = Icons.Default.Close,
                      contentDescription = null,
                      colorFilter = ColorFilter.tint(color = activatedColor))
            }
        }
        Row(modifier = Modifier.selectableGroup()) {
            communicationType.forEach { text ->
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = activatedColor,
                            unselectedColor = onBackgroundColor,
                        )
                    )
                    Text( text = text, fontSize = 15.sp,
                          color = if (text == selectedOption) activatedColor else onBackgroundColor)
                }
            }
        }
        Row(modifier = Modifier.padding(start = 10.dp, bottom = 10.dp, end = 10.dp)) {
            when (selectedOption){
                communicationType[0] -> {
                    LazyColumn (
                        modifier = Modifier.background(color = backgroundColor,
                                                       shape = RoundedCornerShape(5.dp)
                        )
                            .height(70.dp).weight(2f)) {
                        if (listOfCOMPorts.isEmpty())
                            item {
                                Text(text = noCom, color = onBackgroundColor)
                            }
                        else
                            for (port in listOfCOMPorts)
                                item {
                                    TextButton(onClick = { selectedCOMPort = port },
                                               colors = ButtonDefaults.buttonColors(
                                                   backgroundColor = if (selectedCOMPort.equals(port)) activatedColor
                                                   else backgroundColor,
                                                   contentColor = if (selectedCOMPort.equals(port)) onActivatedColor
                                                   else onBackgroundColor
                                               )){
                                        Text(text = port,
                                             color = if (selectedCOMPort.equals(port)) onActivatedColor
                                             else onBackgroundColor,
                                             modifier = Modifier.width(150.dp))
                                    }
                                }
                    }
                }
                else -> {
                    Text (text = "Not yet implemented", color = onBackgroundColor)
                }
            }
            Column (modifier = Modifier.weight(1f).padding(start = 20.dp)) {
                FilledIconButton(onClick = onRefreshClick,
                                 colors = IconButtonColors(
                                     containerColor = backgroundColor,
                                     contentColor = onBackgroundColor,
                                     disabledContainerColor = backgroundColor,
                                     disabledContentColor = onBackgroundColor),
                                 shape = CircleShape,
                                 modifier = Modifier.size(40.dp)
                ){
                    Image(imageVector = Icons.Default.Refresh,
                          contentDescription = null,
                          colorFilter = ColorFilter.tint(color = onBackgroundColor))
                }
                FilledIconButton(onClick = onSelectClick,
                                 colors = IconButtonColors(
                                    containerColor = activatedColor,
                                    contentColor = onActivatedColor,
                                    disabledContainerColor = activatedColor,
                                    disabledContentColor = onActivatedColor),
                                 shape = CircleShape,
                                 modifier = Modifier.size(40.dp)
                ){
                Image(imageVector = Icons.Default.Done,
                      contentDescription = null,
                      colorFilter = ColorFilter.tint(color = onActivatedColor))
                }
            }

        }
    }
}