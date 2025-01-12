package com.example.binchecker.core.di

import com.example.binchecker.domain.api.CardInfoRepository
import com.example.binchecker.domain.usecase.GetCardInfoUseCase
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
}