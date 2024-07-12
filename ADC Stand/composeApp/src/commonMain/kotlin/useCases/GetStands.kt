package useCases

import androidx.compose.runtime.mutableStateOf
import models.Stand

class GetStands {
    fun execute(callback:(List<Stand>) -> Unit){
        StandConnectionWork().Refresh{
            val stands = mutableListOf(Stand(
                availableConnections = mutableStateOf(it)
            ))
//            callback(stands)
        }
        callback(mutableListOf(Stand()))
    }
}