package com.example.binchecker.domain.model

import android.icu.util.Currency

data class CardInfo(
    val cardBin: String?,
    val scheme: String?,
    val brand: String?,
    val country: String?,
    val emoji: String?,
    val currency: String?,
    val latitude: Int?,
    val longitude: Int?,
    val bankName: String?,
    val bankUrl: String?,
    val bankPhone: String?,
    val bankCity: String?,
)
