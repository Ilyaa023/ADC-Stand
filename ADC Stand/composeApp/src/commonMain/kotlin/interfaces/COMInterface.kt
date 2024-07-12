package interfaces

import interfaces.connection.IConnectionDataflow

//expect fun getAvailableCOMPorts(): List<String>
//expect fun checkCOMStand(list: List<String>, checked: (MutableMap<String, Boolean>) -> Unit)
//expect fun getConnectionImpl(str: String, listener: IConnectListener, callback: (IConnectionDataflow) -> Unit)

//object COMConnections: IConnectListener {
//    private var availableConnections = mutableMapOf<String, Boolean> ()
//    private var connections = mutableListOf<IConnectionDataflow>()
//
//
//    fun addConnection(connection: IConnectionDataflow){
//        if (!connections.contains(connection))
//            connections.add(connection)
//    }
//    fun removeConnection(connection: IConnectionDataflow){
//        if (connections.contains(connection))
//            connections.remove(connection)
//    }
//    fun getConnection(index: Int): IConnectionDataflow{
//        if (connections.size > index)
//            return connections[index]
//        return connections[0]
//    }
//    fun Connect(str: String){
//
//        }
//    }
//    override fun callback(outputMessage: String, success: Boolean) {
//
//    }
//}

expect fun getAllInstances(out: (List<IConnectionDataflow>) -> Unit)
//expect fun getBTConnectionInstance(): IConnectionDataflow?
//expect fun getInternetConnectionInstance(): IConnectionDataflow?
//expect fun getCOMConnectionInstance(): IConnectionDataflow?

//object COMInterface: IConnectListener {
//    val instances = mutableMapOf<String, IConnectionDataflow>()
//    fun ConnectCOM(name: String, listener: IConnectListener){
//        if (!instances.keys.contains(name))
//            getCOMConnectionInstance()?.let {
//                instances[name] = it
//            }
//        if (instances.keys.contains(name) && instances[name] != null){
//            instances[name]!!.Connect(name, listener)
//        }
//    }
//
//    fun DisconnectCOM(name: String){
//        instances[name]?.Disconnect(name)
//    }
//
//    fun SendData(name: String, data: String, stringOrByte: Boolean){
//        instances[name]?.SendString(data, this)
//    }
//
//    override fun callback(connectionName: String, outputMessage: String, success: Boolean) {
//        instances[connectionName]
//    }
//}
