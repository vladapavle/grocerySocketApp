package com.emasara.groceryapp.model

import androidx.lifecycle.MutableLiveData

interface BaseRepository<T> {
    fun saveData(data: T)
    fun getAllData(): MutableLiveData<ArrayList<T>>
    fun clearAllData()
    fun connectToChanel()
    fun disconnectFromChanel()
}