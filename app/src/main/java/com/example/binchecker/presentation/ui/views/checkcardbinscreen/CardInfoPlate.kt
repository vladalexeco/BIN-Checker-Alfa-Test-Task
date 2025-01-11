package com.example.binchecker.presentation.ui.views.checkcardbinscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.binchecker.domain.model.CardInfo
import com.example.binchecker.presentation.ui.theme.AccentColor
import com.example.binchecker.presentation.ui.theme.DialogBoxColor
import com.example.binchecker.presentation.ui.theme.LightTextColor
import com.example.binchecker.presentation.ui.theme.MainTextColor

@Composable
fun CardInfoPlate(
    modifier: Modifier = Modifier,
    cardInfo: CardInfo,
    backgroundColor: Color = DialogBoxColor,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            CardRow(
                header = "Card Number/",
                value = cardInfo.cardBin.toString()
            )

            CardRow(
                modifier = Modifier.padding(top = 4.dp),
                header = "Scheme/",
                value = cardInfo.scheme
            )

            CardRow(
                modifier = Modifier.padding(top = 4.dp),
                header = "Brand/",
                value = cardInfo.brand
            )

            CardRow(
                modifier = Modifier.padding(top = 4.dp),
                header = "Country/",
                value = generateFullCountryName(
                    countryValue = cardInfo.country,
                    emojiValue = cardInfo.emoji,
                    currencyValue = cardInfo.currency
                )
            )

            CardRow(
                modifier = Modifier.padding(top = 4.dp),
                header = "Coordinates/",
                value = generateCoordinatesRow(
                    latitudeValue = cardInfo.latitude,
                    longitudeValue = cardInfo.longitude
                )
            )

            BankBlock(
                modifier = Modifier.padding(top = 8.dp),
                header = "Bank/",
                name = cardInfo.bankName,
                url = cardInfo.bankUrl,
                phone = cardInfo.bankPhone,
                city = cardInfo.bankCity
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CardInfoPlatePreview() {
    CardInfoPlate(cardInfo = mockCardInfo)
}

@Composable
fun CardRow(
    modifier: Modifier = Modifier,
    header: String,
    value: String?,
    headerTextStyle: TextStyle = TextStyle(fontSize = 14.sp, color = MainTextColor),
    valueTextStyle: TextStyle = TextStyle(fontSize = 20.sp, color = AccentColor)
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = header,
            style = headerTextStyle
        )

        Text(
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            style = valueTextStyle,
            text = value ?: "-",
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CardRowPreview() {
    CardRow(header = "Header", value = "Value")
}

@Composable
fun BankBlock(
    modifier: Modifier = Modifier,
    header: String,
    name: String?,
    url: String?,
    phone: String?,
    city: String?,
    headerTextStyle: TextStyle = TextStyle(fontSize = 14.sp, color = MainTextColor),
    subHeaderTextStyle: TextStyle = TextStyle(fontSize = 12.sp, color = LightTextColor),
    valueTextStyle: TextStyle = TextStyle(fontSize = 20.sp, color = AccentColor)
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = header,
            style = headerTextStyle
        )

        Text(
            modifier = Modifier.padding(top = 8.dp, start = 12.dp),
            text = "Name: ",
            style = subHeaderTextStyle
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = name ?: "-",
            style = valueTextStyle,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier.padding(top = 4.dp, start = 12.dp),
            text = "Url: ",
            style = subHeaderTextStyle
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = url ?: "-",
            style = valueTextStyle,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier.padding(top = 4.dp, start = 12.dp),
            text = "Phone: ",
            style = subHeaderTextStyle
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = phone ?: "-",
            style = valueTextStyle,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier.padding(top = 4.dp, start = 12.dp),
            text = "City: ",
            style = subHeaderTextStyle
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = city ?: "-",
            style = valueTextStyle,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview(showBackground = true)
fun BankBlockPreview() {
    BankBlock(
        header = "Bank/",
        name = "Name",
        url = "Www",
        phone = "+711111111",
        city = "Moscow"
    )
}

fun generateFullCountryName(
    countryValue: String?,
    emojiValue: String?,
    currencyValue: String?
): String {
    val country = countryValue ?: ""
    val emoji = emojiValue ?: ""
    val currency = if (currencyValue != null) "($currencyValue)" else ""

    val result = "$country $emoji $currency"

    return if (result.isEmpty()) "-" else result
}

fun generateCoordinatesRow(
    latitudeValue: Int?,
    longitudeValue: Int?
) : String {
    return if (latitudeValue == null || longitudeValue == null) "-"
    else "Latitude: $latitudeValue, Longitude: $longitudeValue"
}

val mockCardInfo = CardInfo(
    cardBin = "45675500",
    scheme = "Visa",
    brand = "Visa Classic",
    country = "Russia",
    emoji = "ru",
    currency = "RU",
    latitude = 56,
    longitude = 35,
    bankName = "Sber",
    bankUrl = "www.sber.ru",
    bankPhone = "+711111111",
    bankCity = "Moscow"
)