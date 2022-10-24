package com.emasara.groceryapp.model.local

import androidx.lifecycle.MutableLiveData
import com.emasara.groceryapp.model.BaseRepository
import com.emasara.groceryapp.model.Grocery
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class GroceryRepository : BaseRepository<Grocery>, CoroutineScope {
    private val IP = "wss://superdo-groceries.herokuapp.com/receive"

    private var job: Job = Job()
    private lateinit var client: HttpClient
    private var connectionStatus = MutableLiveData(false)

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val allGroceries: MutableLiveData<ArrayList<Grocery>> =
        MutableLiveData(arrayListOf())

    override fun saveData(data: Grocery) {
        allGroceries.value?.let {
            allGroceries.value!!.add(0, data)
            allGroceries.postValue(allGroceries.value)
        }
    }

    override fun getAllData(): MutableLiveData<ArrayList<Grocery>> = allGroceries

    override fun clearAllData() {

    }

    override fun connectToChanel() {
        connectionStatus.postValue(true)
        launch {
            createClient()
        }
    }

    override fun disconnectFromChanel() {
        connectionStatus.postValue(false)
        client.close()
    }

    private suspend fun createClient() {
        client = HttpClient(CIO) {
            install(WebSockets) {
                pingInterval = 500
            }
        }
        client.webSocket(IP) {
            try {
                for (frame in incoming) {
                    frame as? Frame.Text ?: continue
                    if (connectionStatus.value == true)
                        saveData(Gson().fromJson(frame.readText(), Grocery::class.java))
                }
            } catch (e: Exception) {
                client.close()
            }
        }
    }
}