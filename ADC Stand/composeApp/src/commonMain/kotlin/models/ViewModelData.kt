package models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import useCases.ConnectionWork

data class ViewModelData(
    var isLoading: MutableState<Boolean> = mutableStateOf(false),
    var stands: MutableState<List<Stand>> = mutableStateOf(emptyList()),
    var menuVisibility: MutableState<Boolean> = mutableStateOf(true),
){
    fun refresh(){
        ConnectionWork().Refresh(stands.value.toMutableList()) { standsList, completed ->
            stands.value = standsList
            isLoading.value = !completed
        }
    }
}
