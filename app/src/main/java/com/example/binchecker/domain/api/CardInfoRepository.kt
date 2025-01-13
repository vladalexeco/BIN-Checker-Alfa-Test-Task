package com.example.binchecker.domain.api

import com.example.binchecker.domain.model.CardInfo
import kotlinx.coroutines.flow.Flow

interface CardInfoRepository {

    suspend fun getCardInfo(cardBin: String): Flow<Result<CardInfo>>
}