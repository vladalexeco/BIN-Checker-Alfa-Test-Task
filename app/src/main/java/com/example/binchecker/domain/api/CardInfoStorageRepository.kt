package com.example.binchecker.domain.api

import com.example.binchecker.domain.model.CardInfo
import kotlinx.coroutines.flow.Flow

interface CardInfoStorageRepository {
    suspend fun saveCardInfoToDatabase(cardInfo: CardInfo)
    suspend fun getRequestHistoryFromDatabase(): Flow<List<CardInfo>>
    suspend fun clearCardInfoDatabase()
}