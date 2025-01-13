package com.example.binchecker.presentation.ui.views.bottomnavigationbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.binchecker.presentation.ui.theme.AccentColor
import com.example.binchecker.presentation.ui.theme.BottomBarColor
import com.example.binchecker.presentation.ui.theme.DialogBoxColor
import com.example.binchecker.presentation.ui.theme.MainTextColor
import com.example.binchecker.R
import com.example.binchecker.presentation.ui.theme.BackgroundColor

@Composable
fun CustomBottomNavBar(
    backgroundColor: Color = BottomBarColor,
    topEdgeColor: Color = DialogBoxColor,
    screenRoute1: String = "",
    screenRoute2: String = "",
    currentRoute: String? = "",
    onFocusTint: Color = AccentColor,
    outFocusTint: Color = MainTextColor,
    firstItemText: String = stringResource(R.string.search),
    secondItemText: String = stringResource(R.string.history),
    firstItemImage: Painter = painterResource(R.drawable.credit_card),
    secondItemImage: Painter = painterResource(R.drawable.history),
    firstItemClick: () -> Unit,
    secondItemClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = topEdgeColor)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            NavBottomElement(
                text = firstItemText,
                painter = firstItemImage,
                onFocusTint = onFocusTint,
                outFocusTint = outFocusTint,
                isSelected = currentRoute == screenRoute1,
                onClick = { firstItemClick.invoke() }
            )

            NavBottomElement(
                text = secondItemText,
                painter = secondItemImage,
                onFocusTint = onFocusTint,
                outFocusTint = outFocusTint,
                isSelected = currentRoute == screenRoute2,
                onClick = { secondItemClick.invoke() }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CustomBottomNavBarPreview() {
    var currentRoute by remember { mutableStateOf("one") }

    Scaffold(
        bottomBar = {
            CustomBottomNavBar(
                screenRoute1 = "one",
                screenRoute2 = "two",
                currentRoute = currentRoute,
                firstItemClick = { currentRoute = "one" },
                secondItemClick = { currentRoute = "two" }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(color = BackgroundColor),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Content",
                    style = TextStyle(color = MainTextColor, fontSize = 20.sp)
                )
            }
        }
    )
}