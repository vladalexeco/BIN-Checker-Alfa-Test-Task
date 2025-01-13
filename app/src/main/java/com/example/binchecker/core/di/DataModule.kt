package com.example.binchecker.core.di

import android.content.Context
import androidx.room.Room
import com.example.binchecker.data.api.CardInfoRepositoryImpl
import com.example.binchecker.data.api.CardInfoStorageRepositoryImpl
import com.example.binchecker.data.network.CardInfoApiService
import com.example.binchecker.data.storage.AppDataBase
import com.example.binchecker.domain.api.CardInfoRepository
import com.example.binchecker.domain.api.CardInfoStorageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "database.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCardInfoStorageRepository(appDataBase: AppDataBase) : CardInfoStorageRepository {
        return CardInfoStorageRepositoryImpl(appDataBase)
    }
}