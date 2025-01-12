package com.example.binchecker.data.network

import com.example.binchecker.data.network.dto.CardInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CardInfoApiService {
    @GET("{bin}")
    suspend fun getCardInfo(@Path("bin") bin: String): CardInfoDto
}