package com.example.binchecker.data.api

import com.example.binchecker.data.storage.AppDataBase
import com.example.binchecker.data.storage.model.toCardInfo
import com.example.binchecker.data.storage.model.toCardInfoEntity
import com.example.binchecker.domain.api.CardInfoRepository
import com.example.binchecker.domain.api.CardInfoStorageRepository
import com.example.binchecker.domain.model.CardInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CardInfoStorageRepositoryImpl(
    private val appDataBase: AppDataBase
) : CardInfoStorageRepository{

    override suspend fun saveCardInfoToDatabase(cardInfo: CardInfo) {
        appDataBase
            .getCardInfoDao()
            .insertCardInfoToDatabase(cardInfo.toCardInfoEntity())
    }

    override suspend fun getRequestHistoryFromDatabase(): Flow<List<CardInfo>> = flow {
        val cardInfoEntityList = appDataBase.getCardInfoDao().getRequestCardInfoHistory()
        val cardInfoList = cardInfoEntityList.map { cardInfoEntity -> cardInfoEntity.toCardInfo() }
        emit(cardInfoList)
    }

    override suspend fun clearCardInfoDatabase() {
        appDataBase.getCardInfoDao().clearCardInfoDatabase()
    }
}