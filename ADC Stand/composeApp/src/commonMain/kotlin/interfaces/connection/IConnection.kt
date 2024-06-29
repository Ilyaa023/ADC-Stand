package interfaces.connection

interface IConnectListener{
    fun callback(outputMessage: String, success: Boolean)
}

interface IConnectionsListCallback{
    fun callback (availablePorts: MutableList<String>)
}

interface IConnectionDataflow{
    fun Connect(data: InputDataModel, out: IConnectListener)
    fun Disconnect(data: InputDataModel)
    fun SendData(data: InputDataModel, out: IConnectListener)
    fun CheckStands(callback: (MutableList<String>) -> Unit)
}