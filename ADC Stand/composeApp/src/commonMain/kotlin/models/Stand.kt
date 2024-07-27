package models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import interfaces.connection.IConnectListener

enum class ConnectionType {COM, BT, NET}
enum class StandType {ADC}

data class Stand(
        val connection: Connection,
        var isConnected: MutableState<Boolean> = mutableStateOf(false),
//        var selectedCT: MutableState<ConnectionType> = mutableStateOf(ConnectionType.COM),
        var standType: StandType? = null,
        var buffer: ArrayList<Array<Int>> = ArrayList(),
        var update: MutableState<Boolean> = mutableStateOf(false)
) : IConnectListener {
    init {
        connection.listener = this
    }
    override fun callback(connectionName: String, outputMessage: String, connected: Boolean) {
        if (connected && connectionName == connection.connectionName){
            for (i in 0..99){
                if (outputMessage[i * 6] == 'm' && outputMessage[(i+1) * 6].code == i){
                    val bytes = arrayOf(outputMessage[(i+2) * 6].code, outputMessage[(i+3) * 6].code, outputMessage[(i+4) * 6].code, outputMessage[(i+5) * 6].code)
                    val a = bytes[0]*256 + bytes[1]
                    val b = bytes[2]*256 + bytes[3]
                    buffer.add(arrayOf(a,b))
                }
            }
            update.value = true
        }
    }
}