package com.example.binchecker.presentation.state

const val EMPTY_STRING = ""

data class CheckCardBinScreenState(
    val fieldText: String = EMPTY_STRING,
    val networkStatus: RequestStatus = RequestStatus.Initial
)
