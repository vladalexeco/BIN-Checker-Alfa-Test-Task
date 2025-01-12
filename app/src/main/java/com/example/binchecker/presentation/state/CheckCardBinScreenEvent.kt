package com.example.binchecker.presentation.state

sealed interface CheckCardBinScreenEvent {
    data object ResetState : CheckCardBinScreenEvent
    data class ChangeTextFieldValue(val newValue: String) : CheckCardBinScreenEvent
    data object DoRequest : CheckCardBinScreenEvent
}