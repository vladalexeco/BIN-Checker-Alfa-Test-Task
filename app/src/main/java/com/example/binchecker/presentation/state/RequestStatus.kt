package com.example.binchecker.presentation.state

import com.example.binchecker.domain.model.CardInfo

sealed interface RequestStatus {
    data object Initial : RequestStatus
    data object Request : RequestStatus
    data class Success(val cardInfo: CardInfo) : RequestStatus
    data class Failure(val errorMessage: String) : RequestStatus
}