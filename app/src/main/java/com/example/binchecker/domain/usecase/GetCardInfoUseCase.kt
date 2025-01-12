package com.example.binchecker.domain.usecase

import com.example.binchecker.domain.api.CardInfoRepository
import com.example.binchecker.domain.model.CardInfo
import kotlinx.coroutines.flow.Flow

class GetCardInfoUseCase(
    private val cardInfoRepository: CardInfoRepository
) {
    suspend operator fun invoke(bin: String): Flow<Result<CardInfo>> {
        return cardInfoRepository.getCardInfo(cardBin = bin)
    }
}