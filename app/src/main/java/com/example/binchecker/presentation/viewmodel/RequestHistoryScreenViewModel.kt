package com.example.binchecker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binchecker.domain.usecase.ClearCardInfoDatabaseUseCase
import com.example.binchecker.domain.usecase.GetRequestHistoryFromDatabaseUseCase
import com.example.binchecker.presentation.state.RequestHistoryScreenEvent
import com.example.binchecker.presentation.state.RequestHistoryScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RequestHistoryScreenViewModel @Inject constructor(
    private val getRequestHistoryFromDatabaseUseCase: GetRequestHistoryFromDatabaseUseCase,
    private val clearCardInfoDatabaseUseCase: ClearCardInfoDatabaseUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RequestHistoryScreenState())
    val uiState: StateFlow<RequestHistoryScreenState> = _uiState.asStateFlow()

    init {
        getCardInfoListFromDatabase()
    }

    fun onEvent(requestHistoryScreenEvent: RequestHistoryScreenEvent) {
        when (requestHistoryScreenEvent) {

            RequestHistoryScreenEvent.ClearRequestHistory -> {

                viewModelScope.launch {
                    val clearJob = launch(Dispatchers.IO) {
                        clearRequestHistory()
                    }

                    clearJob.join()

                    getCardInfoListFromDatabase()
                }
            }
        }
    }

    private suspend fun clearRequestHistory() {
            clearCardInfoDatabaseUseCase.invoke()
    }

    private fun getCardInfoListFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            getRequestHistoryFromDatabaseUseCase.invoke().collect { cardInfoList ->
                withContext(Dispatchers.Main) {
                    _uiState.update { requestHistoryScreenState ->
                        requestHistoryScreenState.copy(cardInfoList = cardInfoList)
                    }
                }
            }
        }
    }
}