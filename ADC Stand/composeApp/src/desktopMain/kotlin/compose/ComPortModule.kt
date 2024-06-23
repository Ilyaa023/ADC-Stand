package compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import SPDataflow.SerialPortCommunication
import interfaces.IConnectListener
import interfaces.connection.InputDataModel

class ComPortModule: IConnectListener {
    private val serialPortCommunication = SerialPortCommunication()
    private val noCom = "No COM Ports"
    private var err = mutableStateOf("")
    private var mes = mutableStateOf("")
    private var cnct = mutableStateOf(false)
//    @Composable
//    override fun getModule(){
//        var ports by remember { mutableStateOf(listOf(noCom)) }
//        var error by remember { err }
//        var message by remember { mes }
//        var connected by remember { cnct }
//        Row {
//            Column {
//                if (ports.size == 0)
//                    ports = listOf(noCom)
//                Column {
//                    for (port in ports)
//                        Column {
//
//                            Button (onClick = {
//                                if (connected)
//                                    serialPortCommunication.SendData(InputDataModel("abc"), this@ComPortModule)
//                                else
//                                    serialPortCommunication.Connect(InputDataModel(port), this@ComPortModule)
//                            }){
//                                Text(port)
//                            }
//                        }
//                }
//                if (error.isNotEmpty())
//                    Text(error)
//            }
//            Button(onClick = {
//                ports = serialPortCommunication.getPorts()
//            }){
//                Text("UPD")
//            }
//        }
//
//    }

    override fun callback(outputMessage: String, success: Boolean) {
        if (!success)
            err.value = outputMessage
        cnct.value = success
    }

    override fun receivedMessage(message: String) {
        mes.value = message
    }
}