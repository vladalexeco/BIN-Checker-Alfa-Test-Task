package com.example.binchecker.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.binchecker.presentation.ui.theme.AccentColor
import com.example.binchecker.presentation.ui.theme.BackgroundColor
import com.example.binchecker.presentation.ui.theme.MainTextColor
import com.example.binchecker.presentation.ui.views.checkcardbinscreen.SimpleButton

@Composable
fun CheckCardBinScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
    ) {
        Column(
            modifier = Modifier.padding(top = 48.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var textValue by remember { mutableStateOf("") }

            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = "Enter the first 6 to 8 digits of a card number (BIN/IIN)",
                style = TextStyle(color = MainTextColor, fontSize = 12.sp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                        .border(
                            width = 1.dp,
                            color = AccentColor,
                            shape = RoundedCornerShape(6.dp)
                        ),
                    value = textValue,
                    textStyle = TextStyle(
                        color = MainTextColor,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    ),
                    placeholder = {
                        Text(
                            text = "43215673",
                            style = TextStyle(fontSize = 20.sp, color = MainTextColor.copy(alpha = 0.6f)),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    },
                    onValueChange = { newTextValue ->
                        textValue = newTextValue
                    }
                )

                SimpleButton(
                    modifier = Modifier.fillMaxHeight(),
                    text = "Look Up",
                    onClick = {}
                )
            }


        }

    }
}

@Composable
@Preview(showBackground = true)
fun CheckCardBinScreenPreview() {
    CheckCardBinScreen()
}