package com.example.binchecker.domain.usecase

import com.example.binchecker.domain.api.CardInfoStorageRepository
import com.example.binchecker.domain.model.CardInfo
import kotlinx.coroutines.flow.Flow

class GetRequestHistoryFromDatabaseUseCase(
    private val cardInfoStorageRepository: CardInfoStorageRepository
) {
    suspend operator fun invoke(): Flow<List<CardInfo>> {
        return cardInfoStorageRepository.getRequestHistoryFromDatabase()
    }
}