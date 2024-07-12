package interfaces.connection

import models.ConnectionType

interface IConnectListener{
    fun callback(connectionName: String, outputMessage: String, success: Boolean)
}

interface IConnectionsListCallback{
    fun callback (availablePorts: MutableList<String>)
}

interface IConnectionDataflow{
    var connectionName: String
    val connectionType: ConnectionType
    fun Connect(data: String = connectionName, out: IConnectListener)
    fun Disconnect(data: String = connectionName)
    fun SendString(data: String, out: IConnectListener)
    fun SendByte(data: String, out: IConnectListener)
    fun CheckStands(callback: (MutableList<IConnectionDataflow>) -> Unit)
}