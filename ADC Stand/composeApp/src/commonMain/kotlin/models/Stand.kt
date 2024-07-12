package models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import interfaces.connection.IConnectListener
import interfaces.connection.IConnectionDataflow

enum class ConnectionType {COM, BT, NET}

data class Stand(
        var selectedCT: MutableState<ConnectionType> = mutableStateOf(ConnectionType.COM),
        var availableConnections: MutableState<List<IConnectionDataflow>> = mutableStateOf(emptyList()),
        var connection: MutableState<IConnectionDataflow?> = mutableStateOf(null),
        var isConnected: MutableState<Boolean> = mutableStateOf(false)
) : IConnectListener{
    override fun callback(connectionName: String, outputMessage: String, success: Boolean) {
        TODO("Not yet implemented")
    }
}