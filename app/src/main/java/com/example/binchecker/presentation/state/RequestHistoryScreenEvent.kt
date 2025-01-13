package com.example.binchecker.presentation.state

sealed interface RequestHistoryScreenEvent {
    data object ClearRequestHistory : RequestHistoryScreenEvent
}