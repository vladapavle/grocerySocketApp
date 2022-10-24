package com.emasara.groceryapp.viewmodel

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emasara.groceryapp.model.BaseRepository
import com.emasara.groceryapp.model.Grocery
import com.emasara.groceryapp.model.GroceryRepository

class GroceriesViewModel(private val repository: BaseRepository<Grocery> = GroceryRepository()) :
    ViewModel() {


    private val connectionStatus = MutableLiveData(false)
    private val allGroceriesLiveData = repository.getAllData()
    val selectedItem = MutableLiveData<SelectedItem>()


    fun getAllGroceriesLiveData() = allGroceriesLiveData

    fun deleteAllGroceries() {
        repository.clearAllData()
    }

    fun getConnectionStatus() = connectionStatus

    fun changeConnectionStatus() {
        connectionStatus.postValue(!connectionStatus.value!!)
        if (connectionStatus.value == true) {
            repository.disconnectFromChanel()
        } else {
            repository.connectToChanel()
        }
    }

    fun onItemClick(socketData: Grocery, circleView: ImageView) {
        selectedItem.postValue(SelectedItem(socketData, circleView))
    }

    data class SelectedItem(
        val socketData: Grocery,
        val circleView: ImageView
    )
}