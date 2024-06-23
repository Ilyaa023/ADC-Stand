package interfaces
import interfaces.connection.InputDataModel

enum class Connection{
    COM, BLUETOOTH, NETWORK
}

interface IConnectListener{
    fun callback(outputMessage: String, success: Boolean)
    fun receivedMessage(message: String)
}

interface IConnectionDataflow{
    fun Connect(data: InputDataModel, out: IConnectListener)
    fun SendData(data: InputDataModel, out: IConnectListener)
}
expect fun getAvilableConnectons(): List<Connection>