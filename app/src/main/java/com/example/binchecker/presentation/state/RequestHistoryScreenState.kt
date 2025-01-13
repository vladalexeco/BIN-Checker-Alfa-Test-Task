package com.example.binchecker.presentation.state

import com.example.binchecker.domain.model.CardInfo

data class RequestHistoryScreenState(
    val cardInfoList: List<CardInfo> = emptyList()
)
