package com.example.binchecker.data.network.dto

import com.example.binchecker.domain.model.CardInfo

data class CardInfoDto(
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val country: CountryDto?,
    val bank: BankDto?
)

fun CardInfoDto.toCardInfo(): CardInfo {
    return CardInfo(
        cardBin = null,
        scheme = this.scheme,
        brand = this.brand,
        country = this.country?.name,
        emoji = this.country?.emoji,
        currency = this.country?.currency,
        latitude = this.country?.latitude,
        longitude = this.country?.longitude,
        bankName = this.bank?.name,
        bankUrl = this.bank?.url,
        bankPhone = this.bank?.phone,
        bankCity = this.bank?.city
    )
}
