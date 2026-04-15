package com.example.stockdemo.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockdemo.apiservice.OpenApiTaiFexAipInstance
import com.example.stockdemo.data.StockAll
import com.example.stockdemo.data.StockAvg
import com.example.stockdemo.data.StockBWIBBU
import com.example.stockdemo.repository.OpenApiTaiFexRepository
import com.example.stockdemo.ui.StockSortType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StockAllViewModel : ViewModel() {
    private val tag = "tag_stock_all_view_model"

    private val repository = OpenApiTaiFexRepository

    val previewStockAll = repository.previewStockAll

    fun setPreviewStockAll() {
        _stockAllState.value = listOf(
            repository.previewStockAll
        )
    }

    fun setPreviewStockAvg() {
        _stockAvgAllState.value = repository.previewStockAvg
    }

    fun setPreviewStockBWIBBUCode() {
        _stockCodeState.value = repository.previewStockBWIBBUCode
    }
    fun setPreviewStockBWIBBU() {
        _stockBWIBBUAllState.value = repository.previewStockBWIBBU
    }

    private val apiInstance = OpenApiTaiFexAipInstance.api

    private val _stockAllState = MutableStateFlow(emptyList<StockAll>())
    val stockAllState: StateFlow<List<StockAll>> = _stockAllState.asStateFlow()

    private val _stockAvgAllState = MutableStateFlow(emptyList<StockAvg>())
    val stockAvgAllState: StateFlow<List<StockAvg>> = _stockAvgAllState.asStateFlow()

    private val _stockBWIBBUAllState = MutableStateFlow(emptyList<StockBWIBBU>())
    val stockBWIBBUAllState: StateFlow<List<StockBWIBBU>> = _stockBWIBBUAllState.asStateFlow()

    private val _stockCodeState = MutableStateFlow<String?>(null)
    val stockCodeState: StateFlow<String?> = _stockCodeState.asStateFlow()

    fun setStockCodeState(value: String?) {
        _stockCodeState.value = value
    }

    private val _refreshState = MutableStateFlow(false)
    val refreshState: StateFlow<Boolean> = _refreshState.asStateFlow()

    fun setRefreshState(state: Boolean) {
        _refreshState.value = state
    }

    private val _showSortTypeState = MutableStateFlow(false)
    val showSortTypeState: StateFlow<Boolean> = _showSortTypeState.asStateFlow()

    fun setShowSortTypeState(state: Boolean) {
        _showSortTypeState.value = state
    }

    private val _sortTypeState = MutableStateFlow(StockSortType.SortedByCodeDescending)
    var sortTypeState: StateFlow<StockSortType> = _sortTypeState.asStateFlow()

    fun setSortTypeState(state: StockSortType) {
        _sortTypeState.value = state
    }

    private val _internetConnectionState = MutableStateFlow(false)
    val internetConnectionState: StateFlow<Boolean> = _internetConnectionState.asStateFlow()

    fun setInternetConnectionState(state: Boolean) {
        _internetConnectionState.value = state
    }

    fun onRefresh() {
        if (internetConnectionState.value) {
            setRefreshState(true)
            viewModelScope.launch(Dispatchers.IO) {
                _stockAllState.update {
                    when (sortTypeState.value) {
                        StockSortType.SortedByCodeAscending ->
                            getStockAll().sortedBy { it.code }

                        StockSortType.SortedByCodeDescending ->
                            getStockAll().sortedByDescending { it.code }

                        /*StockSortType.SortedByNameAscending ->
                            getStockAll().sortedBy { it.name }

                        StockSortType.SortedByNameDescending ->
                            getStockAll().sortedByDescending { it.name }*/
                    }
                }
                _stockAvgAllState.update {
                    getStockAvg()
                }
                _stockBWIBBUAllState.update {
                    getStockBWIBBU()
                }
                setRefreshState(false)
            }
        } else {
            setRefreshState(true)
            viewModelScope.launch(Dispatchers.IO) {
                val items = _stockAllState.value
                _stockAllState.update {
                    when (sortTypeState.value) {
                        StockSortType.SortedByCodeAscending ->
                            items.sortedBy { it.code }

                        StockSortType.SortedByCodeDescending ->
                            items.sortedByDescending { it.code }

                        /*StockSortType.SortedByNameAscending ->
                            items.sortedBy { it.name }

                        StockSortType.SortedByNameDescending ->
                            items.sortedByDescending { it.name }*/
                    }
                }
                setRefreshState(false)
            }
        }
    }

    fun fetchStockAll() {
        if (internetConnectionState.value) {
            viewModelScope.launch(Dispatchers.IO) {
                _stockAllState.update {
                    when (sortTypeState.value) {
                        StockSortType.SortedByCodeAscending ->
                            getStockAll().sortedBy { it.code }

                        StockSortType.SortedByCodeDescending ->
                            getStockAll().sortedByDescending { it.code }

                        /*StockSortType.SortedByNameAscending ->
                            getStockAll().sortedBy { it.name }

                        StockSortType.SortedByNameDescending ->
                            getStockAll().sortedByDescending { it.name }*/
                    }
                }
            }
        }
    }
    private suspend fun getStockAll(): List<StockAll> {
        try {
            val response = apiInstance.getStockAll()
            Log.d(tag, "Get Stock All Data: $response")
            if (response.isSuccessful) {
                if (response.body() != null) {
                    return response.body()!!
                }
            } else {
                Log.e(tag, "Get Stock All Fail: ${response.message()}")
            }
            return emptyList()
        } catch (e: Exception) {
            Log.e(tag, "Get Stock All Error: ${e.message}")
            return emptyList()
        }
    }

    fun fetchStockAvg() {
        if (internetConnectionState.value) {
            viewModelScope.launch(Dispatchers.IO) {
                _stockAvgAllState.update {
                    getStockAvg()
                }
            }
        }
    }
    private suspend fun getStockAvg(): List<StockAvg> {
        try {
            val response = apiInstance.getStockAvg()
            Log.d(tag, "Get Stock Avg Data: $response")
            if (response.isSuccessful) {
                if (response.body() != null) {
                    return response.body()!!
                }
            } else {
                Log.e(tag, "Get Stock Avg Fail: ${response.message()}")
            }
            return emptyList()
        } catch (e: Exception) {
            Log.e(tag, "Get Stock Avg Error: ${e.message}")
            return emptyList()
        }
    }

    fun fetchStockBWIBBU() {
        if (internetConnectionState.value) {
            viewModelScope.launch(Dispatchers.IO) {
                _stockBWIBBUAllState.update {
                    getStockBWIBBU()
                }
            }
        }
    }
    private suspend fun getStockBWIBBU(): List<StockBWIBBU> {
        try {
            val response = apiInstance.getStockBWIBBU()
            Log.d(tag, "Get StockBWIBBU Data: $response")
            if (response.isSuccessful) {
                if (response.body() != null) {
                    return response.body()!!
                }
            } else {
                Log.e(tag, "Get StockBWIBBU Fail: ${response.message()}")
            }
            return emptyList()
        } catch (e: Exception) {
            Log.e(tag, "Get StockBWIBBU Error: ${e.message}")
            return emptyList()
        }
    }
}
