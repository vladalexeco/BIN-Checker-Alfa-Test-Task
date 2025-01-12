package com.example.binchecker.core.di

import com.example.binchecker.data.api.CardInfoRepositoryImpl
import com.example.binchecker.data.network.CardInfoApiService
import com.example.binchecker.domain.api.CardInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://lookup.binlist.net/"

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCardInfoApiService(retrofit: Retrofit): CardInfoApiService {
        return retrofit.create(CardInfoApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCardInfoRepository(cardInfoApiService: CardInfoApiService)
    : CardInfoRepository {
        return CardInfoRepositoryImpl(cardInfoApiService = cardInfoApiService)
    }
}