package com.example.binchecker.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.binchecker.domain.model.CardInfo

@Entity(tableName = "card_info_table")
data class CardInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
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

fun CardInfoEntity.toCardInfo(): CardInfo {
    return CardInfo(
        cardBin = this.cardBin,
        scheme = this.scheme,
        brand = this.brand,
        country = this.country,
        emoji = this.emoji,
        currency = this.currency,
        latitude = this.latitude,
        longitude = this.longitude,
        bankName = this.bankName,
        bankUrl = this.bankUrl,
        bankPhone = this.bankPhone,
        bankCity = this.bankCity
    )
}

fun CardInfo.toCardInfoEntity(): CardInfoEntity {
    return CardInfoEntity(
        cardBin = this.cardBin,
        scheme = this.scheme,
        brand = this.brand,
        country = this.country,
        emoji = this.emoji,
        currency = this.currency,
        latitude = this.latitude,
        longitude = this.longitude,
        bankName = this.bankName,
        bankUrl = this.bankUrl,
        bankPhone = this.bankPhone,
        bankCity = this.bankCity
    )
}

