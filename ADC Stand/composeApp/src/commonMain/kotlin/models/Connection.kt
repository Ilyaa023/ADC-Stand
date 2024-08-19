package models

import interfaces.connection.IConnectListener
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

open class Connection(val connectionName: String, var listener: IConnectListener?){
//    var connectionName: String
    val connectionTestString = "connection test"
    open val connectionType: ConnectionType? = null

    private var isConnected = false
    private var isSll = false
    private var graphA = 0
    private var graphB = 3000
//    open fun CheckStand(callback: (Connection) -> Unit){}
    open fun Connect() =
        runBlocking{
            launch {
                delay(50)
                isConnected = true
                listener?.callback(connectionName, connectionTestString, isConnected)
            }.join()
        }
    open fun Disconnect() =
        runBlocking{
            launch {
//                delay(50)
                isConnected = false
                listener?.callback(connectionName, connectionTestString, isConnected)
            }.join()
        }
    open fun SendString(data: String) =
        runBlocking{
            launch {
//                delay(50)
                when (data){
                    "s" -> listener?.callback(connectionName, "ADC", isConnected)
                    "sll" -> {
                        isSll = !isSll
                        thread {
                            while (isSll){
                                var outStr = ""
                                for (i in 0..99){
                                    graphA += 170
                                    if (graphA > 16383) graphA = 0
                                    graphB += 170
                                    if (graphB > 16383) graphB = 0
                                    val firstGraph = byteArrayOf((graphA / 128).toByte(), (graphA % 128).toByte())
                                    val secondGraph = byteArrayOf((graphB / 128).toByte(), (graphB % 128).toByte())
                                    outStr += "m${byteArrayOf(i.toByte()).decodeToString()}${firstGraph.decodeToString()}${secondGraph.decodeToString()}"
                                }
                                listener?.callback(connectionName, outStr, isConnected)
                                Thread.sleep(50)
                            }
                        }
                    }
                    else -> {
                        BadRequestResponce()
                    }
                }
            }.join()
        }
    open fun SendByte(data: Byte){}

    private fun BadRequestResponce(){
        listener?.callback(connectionName, "{ \"request\": \"Bad request\" }", isConnected)
    }
}

