package com.example.binchecker.presentation.viewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binchecker.domain.usecase.GetCardInfoUseCase
import com.example.binchecker.presentation.state.CheckCardBinScreenEvent
import com.example.binchecker.presentation.state.CheckCardBinScreenState
import com.example.binchecker.presentation.state.RequestStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CheckCardBinViewModel @Inject constructor(
    private val getCardInfoUseCase: GetCardInfoUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CheckCardBinScreenState())
    val uiState: StateFlow<CheckCardBinScreenState> = _uiState.asStateFlow()

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun onEvent(checkCardBinScreenEvent: CheckCardBinScreenEvent) {
        when (checkCardBinScreenEvent) {
            is CheckCardBinScreenEvent.ChangeTextFieldValue -> {
                setTextForTextField(checkCardBinScreenEvent.newValue)
            }

            CheckCardBinScreenEvent.DoRequest -> {
                getCardInfo(cardBin = _uiState.value.fieldText)
            }

            CheckCardBinScreenEvent.ResetState -> {
                resetState()
            }
        }
    }

    fun resetState(){
        _uiState.value = CheckCardBinScreenState()
    }

    private fun setTextForTextField(newText: String) {
        _uiState.update { checkCardBinScreenState ->
            checkCardBinScreenState.copy(
                fieldText = newText
            )
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getCardInfo(cardBin: String) {
        _uiState.update { checkCardBinScreenState ->
            checkCardBinScreenState.copy(
                networkStatus = RequestStatus.Request
            )
        }

        viewModelScope.launch {
            getCardInfoUseCase.invoke(bin = cardBin).collect { result ->
                when {
                    result.isSuccess -> {
                        val cardInfo = result.getOrNull()

                        if (cardInfo != null) {
                            _uiState.update { checkCardBinScreenState ->
                                checkCardBinScreenState.copy(
                                    networkStatus = RequestStatus.Success(
                                        cardInfo = cardInfo.copy(cardBin = _uiState.value.fieldText)
                                    )
                                )
                            }
                        } else {
                            _uiState.update { checkCardBinScreenState ->
                                checkCardBinScreenState.copy(
                                    networkStatus = RequestStatus.Failure(
                                        errorMessage = "Unknown Error: Empty response"
                                    )
                                )
                            }
                        }
                    }

                    result.isFailure -> {
                        val exception = result.exceptionOrNull()

                        if (exception != null) {

                            val errorMessage = handleApiError(exception)

                            _uiState.update { checkCardBinScreenState ->
                                checkCardBinScreenState.copy(
                                    networkStatus = RequestStatus.Failure(errorMessage = errorMessage)
                                )
                            }
                        } else {
                            _uiState.update { checkCardBinScreenState ->
                                checkCardBinScreenState.copy(
                                    networkStatus = RequestStatus.Failure(
                                        errorMessage = "Unknown error: ..."
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun handleApiError(error: Throwable): String {
        return when (error) {
            is HttpException -> {
                val httpCode = error.response()?.code()

                if (httpCode == 429) {
                    "HTTP Error: $httpCode - Too many requests"
                } else {
                    "HTTP Error: $httpCode - ${error.message()}"
                }

            }
            is IOException -> {
                "Network Error: ${error.message}"
            }
            else -> {
                "Unknown Error: ${error.message}"
            }
        }
    }
}