package useCases

import interfaces.connection.IConnectListener
import interfaces.connection.getAllInstances
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import models.Connection
import models.Stand
import models.StandType
import kotlin.concurrent.thread

class ConnectionWork() {
    /**
     * Gets all COM, BT and Internet connections and check if it contains in [actualStands]. If doesn't then adds, or remove if vice versa.
     * Result comes with [callback] that have two parameters: List of all stands that available and connected (expands [actualStands]),
     * and Boolean param that means loading state. If it's false then loading in process, and all processes ended when it's true
     */
    fun Refresh(actualStands: MutableList<Stand> = mutableListOf(), callback: (List<Stand>, Boolean) -> Unit){
        callback(actualStands, false)
        getAllInstances{
            println(it)
            val connectionsToCheck = mutableListOf<Connection>()
            it.forEach { connection ->
                var isStandContainsInIt = false
                actualStands.forEach { stand ->
                    if (connection.connectionName == stand.connection.connectionName)
                        isStandContainsInIt = true
                }
                if (!isStandContainsInIt)
                    connectionsToCheck.add(connection)
            }
            println("actual stands $actualStands")
            val standsToRemove = mutableListOf<Stand>()
            actualStands.forEach { stand ->
                println("begin cycle $actualStands")
                var isItContainsInStand = false
//                connectionNames.add(stand.connection.connectionName)
                it.forEach { connection ->
                    if (stand.connection.connectionName == connection.connectionName)
                        isItContainsInStand = true
                }
                if (!isItContainsInStand)
                    standsToRemove.add(stand)
                println("end cycle $actualStands")
            }
            standsToRemove.forEach { stand ->
                actualStands.remove(stand)
            }
            println("connections to check: $connectionsToCheck")
            var checkedNum = 0
            thread {
                runBlocking {
                    val jobs = mutableListOf<Job>()
                    connectionsToCheck.forEach { connection ->
                        println("checking connection $connection")
                        connection.listener = object : IConnectListener{
                            override fun callback(
                                    connectionName: String,
                                    outputMessage: String,
                                    connected: Boolean
                            ) {
                                println("$connection aka $connectionName sent $outputMessage with state $connected")
                                if (connected){
                                    if (outputMessage == connection.connectionTestString){
                                        println("\trealised as test string")
                                        connection.SendString("stand")
                                    }
                                    if  (outputMessage == "ADC" || outputMessage == "{ \"request\": \"Bad request\" }") {
                                        println("\trealised as ADC")
                                        actualStands.add(
                                            Stand(
                                                connection = connection, standType = StandType.ADC
                                            )
                                        )
                                        checkedNum++
                                        callback(actualStands, checkedNum == connectionsToCheck.size)
                                        connection.Disconnect()
                                    }
                                } else {
                                    checkedNum++
                                    callback(actualStands, checkedNum == connectionsToCheck.size)
                                }
                            }
                        }
                        println("connecting $connection")
                        val job = launch { connection.Connect() }
                        jobs.add(job)
                    }
                    jobs.forEach { job ->
                        job.join()
                    }
                    callback(actualStands, true)
                }
            }
        }
    }
    fun Connect(stand: Stand){
        stand.connection.listener = stand
        stand.connection.Connect()
    }
    fun Disconnect(stand: Stand){
        stand.connection.Disconnect()
    }
}