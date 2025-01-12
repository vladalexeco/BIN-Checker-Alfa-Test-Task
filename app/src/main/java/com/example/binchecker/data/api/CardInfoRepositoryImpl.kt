package com.example.binchecker.data.api

import com.example.binchecker.data.network.CardInfoApiService
import com.example.binchecker.data.network.dto.toCardInfo
import com.example.binchecker.domain.api.CardInfoRepository
import com.example.binchecker.domain.model.CardInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class CardInfoRepositoryImpl(
    private val cardInfoApiService: CardInfoApiService
) : CardInfoRepository {
    override suspend fun getCardInfo(cardBin: String): Flow<Result<CardInfo>> = flow {
            try {
                val cardInfoDto = cardInfoApiService.getCardInfo(bin = cardBin)
                emit(Result.success(cardInfoDto.toCardInfo()))
            } catch (t: Throwable) {
                emit(Result.failure(t))
            }
    }
}