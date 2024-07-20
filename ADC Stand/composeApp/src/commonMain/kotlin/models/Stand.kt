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
        var standType: StandType? = null
) : IConnectListener {
    init {
        connection.listener = this
    }
    override fun callback(connectionName: String, outputMessage: String, connected: Boolean) {
//        TODO("Not yet implemented")
    }
}