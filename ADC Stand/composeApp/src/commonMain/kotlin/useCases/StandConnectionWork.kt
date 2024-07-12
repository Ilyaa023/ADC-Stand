package useCases

import interfaces.connection.IConnectListener
import interfaces.connection.IConnectionDataflow
import interfaces.getAllInstances
import models.Stand

class StandConnectionWork {
    fun Refresh(callback: (List<IConnectionDataflow>) -> Unit){
        var instances = emptyList<IConnectionDataflow>()
        getAllInstances{
            instances = it
        }
        println(instances)
        val availableInstances = mutableListOf<IConnectionDataflow>()
        instances.forEach {
            it.CheckStands { available ->
                available.forEach {avOne ->
                    availableInstances.add(avOne)
                }
                callback(availableInstances)
            }
        }
    }
    fun Connect(stand: Stand, listener: IConnectListener){
        if (stand.connection.value == null) {
//            stand.connection.value = connection
            stand.connection.value!!.Connect(stand.connection.value!!.connectionName, listener)
        }
    }
    fun Disconnect(stand: Stand){
        stand.connection.value?.let { it.Disconnect(it.connectionName) }
        stand.connection.value = null
    }
}