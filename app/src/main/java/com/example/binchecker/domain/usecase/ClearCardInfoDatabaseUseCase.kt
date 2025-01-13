package com.example.binchecker.domain.usecase

import com.example.binchecker.domain.api.CardInfoStorageRepository

class ClearCardInfoDatabaseUseCase(
    private val cardInfoStorageRepository: CardInfoStorageRepository
) {
    suspend operator fun invoke() {
        cardInfoStorageRepository.clearCardInfoDatabase()
    }
}