package ui.pages

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import models.ViewModelData

@Composable
fun GraphPage (vmData: ViewModelData) {
    var update by remember { vmData.selectedStand.value!!.update }
    Column(modifier = Modifier.width(740.dp).padding(top = 55.dp)
        .background(color = MaterialTheme.colorScheme.outlineVariant),
           horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier.padding(20.dp).fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Text("ADC", color = Color.Cyan)
            Box(modifier = Modifier.width(40.dp))
            Text("OIN", color = Color.Magenta)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Button(onClick = { vmData.selectedStand.value?.connection?.SendString("sll") }){
                    Image(Icons.Default.PlayArrow, null)
                }
            }
        }
        Canvas(Modifier.padding(20.dp).height(500.dp).width(700.dp)){
            val points = mutableListOf<Array<Offset>>()
//            var isReverse = false
            var counter = 0
            if (update){
                val bufSize = vmData.selectedStand.value?.buffer!!.size
                if (bufSize > 700){
//                    isReverse = true
                    for (i in 0 .. 699){
                        points.add(arrayOf(
                            Offset((700 - i).toFloat(), (1 - vmData.selectedStand.value?.buffer!![bufSize - i - 1][0] / 16384f) * 500),
                            Offset((700 - i).toFloat(), (1 - vmData.selectedStand.value?.buffer!![bufSize - i - 1][1] / 16384f) * 500)
                        ))
                    }
                } else {
//                    isReverse = false
                    vmData.selectedStand.value?.buffer!!.forEach {
                        points.add(
                            arrayOf(
                                Offset(counter.toFloat(), (1 - it[0] / 16384f) * 500),
                                Offset(counter++.toFloat(), (1 - it[1] / 16384f) * 500)
                            )
                        )
                    }
                }
                if (points.size > 700)
                    vmData.lastPoints = points.subList(points.size - 700, points.size - 1)
                else
                    vmData.lastPoints = points
                update = false
            }
            val lastIndex = vmData.lastPoints.size - 2
            for (currentIndex in 0..lastIndex){
                vmData.lastPoints.let {
//                    if(isReverse) {
//                        drawLine(color = Color.Cyan, it[lastIndex - currentIndex + 1][0], it[lastIndex - currentIndex][0])
//                        drawLine(color = Color.Magenta, it[lastIndex - currentIndex + 1][1], it[lastIndex - currentIndex][1])
//                    } else {
                        drawLine(color = Color.Cyan, it[currentIndex][0], it[currentIndex + 1][0])
                        drawLine(color = Color.Magenta, it[currentIndex][1], it[currentIndex + 1][1])
//                    }
                }
            }
        }

    }
}