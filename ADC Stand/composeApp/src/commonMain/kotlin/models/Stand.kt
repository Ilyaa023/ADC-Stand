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
        isConnected.value = connected
        if (connected && connectionName == connection.connectionName){
            try {
                if (outputMessage.length > 1) {
                    if (outputMessage.length == 600) {
                        for (i in 0..99) {
//                            println("decoded ${outputMessage[i * 6]} ${outputMessage[i * 6 + 1].code} ${outputMessage[i * 6 + 2].code} ${outputMessage[i * 6 + 3].code} ${outputMessage[i * 6 + 4].code} ${outputMessage[i * 6 + 5].code}")
                            if (outputMessage[i * 6] == 'm' && outputMessage[i * 6 + 1].code == i) {
                                val bytes = arrayOf(
                                    outputMessage[i * 6 + 2].code,
                                    outputMessage[i * 6 + 3].code,
                                    outputMessage[i * 6 + 4].code,
                                    outputMessage[i * 6 + 5].code
                                )
                                val a = bytes[0] * 128 + bytes[1]
                                val b = bytes[2] * 128 + bytes[3]
//                                println("point num ${outputMessage[i * 6 + 1].code} decoded $a, $b")
                                buffer.add(arrayOf(a, b))
//                                println(buffer.size)
                            }
                        }
                        update.value = true
                    }
                }
            } catch (e: Exception){
                println(e.message)
            }
        }
    }
}