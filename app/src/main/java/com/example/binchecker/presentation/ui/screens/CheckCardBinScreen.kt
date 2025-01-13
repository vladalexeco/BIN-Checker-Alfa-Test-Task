package com.example.binchecker.presentation.ui.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresExtension
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.binchecker.presentation.state.CheckCardBinScreenEvent
import com.example.binchecker.presentation.state.CheckCardBinScreenSideEffect
import com.example.binchecker.presentation.state.CheckCardBinScreenState
import com.example.binchecker.presentation.state.RequestStatus
import com.example.binchecker.presentation.ui.theme.AccentColor
import com.example.binchecker.presentation.ui.theme.BackgroundColor
import com.example.binchecker.presentation.ui.theme.MainTextColor
import com.example.binchecker.presentation.ui.theme.WrongAnswerColor
import com.example.binchecker.presentation.ui.views.checkcardbinscreen.CardInfoPlate
import com.example.binchecker.presentation.ui.views.checkcardbinscreen.SimpleButton
import com.example.binchecker.presentation.viewmodel.CheckCardBinViewModel
import kotlinx.coroutines.flow.collect

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CheckCardBinScreen() {

    val context = LocalContext.current

    val viewModel: CheckCardBinViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    val navBackStackEntry = LocalLifecycleOwner.current.lifecycle.currentState

    LaunchedEffect(navBackStackEntry) {
        if (navBackStackEntry == Lifecycle.State.RESUMED) {
            viewModel.onEvent(CheckCardBinScreenEvent.ResetState)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is CheckCardBinScreenSideEffect.ShowMessage -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    CheckCardBinScreen(
        state = state,
        onEvent = { checkCardBinScreenEvent ->
            viewModel.onEvent(checkCardBinScreenEvent)
        }
    )
}

@Composable
fun CheckCardBinScreen(
    state: CheckCardBinScreenState,
    onEvent: (CheckCardBinScreenEvent) -> Unit
) {

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
                    value = state.fieldText,
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
                        onEvent.invoke(CheckCardBinScreenEvent.ChangeTextFieldValue(
                            newValue = newTextValue
                        ))
                    }
                )

                SimpleButton(
                    modifier = Modifier.fillMaxHeight(),
                    text = "Look Up",
                    onClick = {
                        onEvent.invoke(CheckCardBinScreenEvent.DoRequest)
                    }
                )
            }
        }

        when(state.networkStatus) {
            RequestStatus.Initial -> {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "BIN Checker by Vladimir Bolshakov",
                    style = TextStyle(fontSize = 14.sp, color = MainTextColor)
                )
            }
            RequestStatus.Request -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is RequestStatus.Success -> {
                CardInfoPlate(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 16.dp),
                    cardInfo = state.networkStatus.cardInfo,
                )
            }
            is RequestStatus.Failure -> {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = state.networkStatus.errorMessage,
                    style = TextStyle(fontSize = 14.sp, color = WrongAnswerColor)
                )
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun CheckCardBinScreenPreview() {
    CheckCardBinScreen(
        state = CheckCardBinScreenState(),
        onEvent = {}
    )
}