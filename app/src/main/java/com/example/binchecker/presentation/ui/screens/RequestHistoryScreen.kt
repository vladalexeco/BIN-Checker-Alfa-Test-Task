package com.example.binchecker.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.binchecker.R
import com.example.binchecker.presentation.state.RequestHistoryScreenEvent
import com.example.binchecker.presentation.state.RequestHistoryScreenState
import com.example.binchecker.presentation.ui.theme.BackgroundColor
import com.example.binchecker.presentation.ui.theme.MainTextColor
import com.example.binchecker.presentation.ui.views.checkcardbinscreen.CardInfoPlate
import com.example.binchecker.presentation.ui.views.checkcardbinscreen.SimpleButton
import com.example.binchecker.presentation.viewmodel.RequestHistoryScreenViewModel

@Composable
fun RequestHistoryScreen() {

    val viewModel: RequestHistoryScreenViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    RequestHistoryScreen(
        state = state,
        onEvent = { requestHistoryScreenEvent ->
            viewModel.onEvent(requestHistoryScreenEvent)
        }
    )
}

@Composable
fun RequestHistoryScreen(
    state: RequestHistoryScreenState,
    onEvent: (RequestHistoryScreenEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(R.string.history_of_requests),
                    style = TextStyle(fontSize = 14.sp, color = MainTextColor)
                )

                SimpleButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = stringResource(R.string.clear),
                    onClick = {
                        onEvent(RequestHistoryScreenEvent.ClearRequestHistory)
                    }
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 68.dp, top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.cardInfoList) { cardInfo ->
                    CardInfoPlate(cardInfo = cardInfo)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RequestHistoryScreenPreview() {
    RequestHistoryScreen(
        state = RequestHistoryScreenState(),
        onEvent = {}
    )
}