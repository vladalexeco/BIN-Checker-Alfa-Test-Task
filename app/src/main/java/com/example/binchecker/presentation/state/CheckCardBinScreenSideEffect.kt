package com.example.binchecker.presentation.state

sealed interface CheckCardBinScreenSideEffect {
    data class ShowMessage(val message: String) : CheckCardBinScreenSideEffect
}