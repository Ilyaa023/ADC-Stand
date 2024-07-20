package interfaces.connection

interface IConnectListener{
    fun callback(connectionName: String, outputMessage: String, connected: Boolean)
}

interface IConnectionsListCallback{
    fun callback (availablePorts: MutableList<String>)
}
