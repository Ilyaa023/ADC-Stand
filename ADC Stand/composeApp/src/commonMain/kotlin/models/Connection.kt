package models

import interfaces.connection.IConnectListener

abstract class Connection(val connectionName: String, var listener: IConnectListener?){
    //    var connectionName: String
    val connectionTestString = "connection test"
    open val connectionType: ConnectionType? = null
//    open fun CheckStand(callback: (Connection) -> Unit){}
    open fun Connect(){}
    open fun Disconnect(){}
    open fun SendString(data: String){}
    open fun SendByte(data: Byte){}
}