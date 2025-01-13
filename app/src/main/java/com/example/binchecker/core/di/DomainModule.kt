package com.example.binchecker.core.di

import com.example.binchecker.domain.api.CardInfoRepository
import com.example.binchecker.domain.api.CardInfoStorageRepository
import com.example.binchecker.domain.usecase.ClearCardInfoDatabaseUseCase
import com.example.binchecker.domain.usecase.GetCardInfoUseCase
import com.example.binchecker.domain.usecase.GetRequestHistoryFromDatabaseUseCase
import com.example.binchecker.domain.usecase.SaveCardInfoToDatabaseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetCardInfoUseCase(cardInfoRepository: CardInfoRepository)
    : GetCardInfoUseCase {
        return GetCardInfoUseCase(cardInfoRepository = cardInfoRepository)
    }

    @Provides
    fun provideSaveCardInfoToDatabaseUseCase(
        cardInfoStorageRepository: CardInfoStorageRepository
    ): SaveCardInfoToDatabaseUseCase {
        return SaveCardInfoToDatabaseUseCase(cardInfoStorageRepository)
    }

    @Provides
    fun provideGetRequestHistoryFromDatabaseUseCase(
        cardInfoStorageRepository: CardInfoStorageRepository
    ): GetRequestHistoryFromDatabaseUseCase {
        return GetRequestHistoryFromDatabaseUseCase(cardInfoStorageRepository)
    }

    @Provides
    fun provideClearCardInfoDatabaseUseCase(
        cardInfoStorageRepository: CardInfoStorageRepository
    ): ClearCardInfoDatabaseUseCase {
        return ClearCardInfoDatabaseUseCase(cardInfoStorageRepository)
    }
}