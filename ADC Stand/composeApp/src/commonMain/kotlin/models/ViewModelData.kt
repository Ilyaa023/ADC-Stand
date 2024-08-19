package models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import useCases.ConnectionWork

data class ViewModelData(
        var isLoading: MutableState<Boolean> = mutableStateOf(false),
//        var stands: MutableState<List<Stand>> = mutableStateOf(mutableListOf(Stand(Connection("virtual device", null)))),
        var stands: MutableState<List<Stand>> = mutableStateOf(emptyList()),
        var menuVisibility: MutableState<Boolean> = mutableStateOf(true),
        var selectedStand: MutableState<Stand?> = mutableStateOf(null),
        var lastPoints: MutableList<Array<Offset>> = mutableListOf()
){
    fun refresh(){
        println(stands.value)
        ConnectionWork().Refresh(stands.value.toMutableList()) { standsList, completed ->
            stands.value = standsList
            isLoading.value = !completed
        }
    }
}
