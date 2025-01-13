package com.example.binchecker.domain.usecase

import com.example.binchecker.domain.api.CardInfoStorageRepository
import com.example.binchecker.domain.model.CardInfo

class SaveCardInfoToDatabaseUseCase(
    private val cardInfoStorageRepository: CardInfoStorageRepository
) {
    suspend operator fun invoke(cardInfo: CardInfo) {
        cardInfoStorageRepository.saveCardInfoToDatabase(cardInfo)
    }
}