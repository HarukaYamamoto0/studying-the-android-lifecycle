package com.harukadev.studyinglifecycle.homescreen

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.harukadev.studyinglifecycle.core.presentation.ObserveAsEvents

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel,
    onAction: (HomeScreenActions) -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    var text by remember { mutableStateOf(state.text) }
    val context = LocalContext.current

    ObserveAsEvents(viewModel.events, debounceMillis = 1000) {
        when (it) {
            is HomeScreenEvent.Error -> {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT)
                    .show()
            }

            is HomeScreenEvent.NavigateToReceiver -> {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, text)
                    flags =
                        Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(sendIntent)
            }
        }
    }
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Saved Text: $text")

        Spacer(Modifier.size(16.dp))

        TextField(
            value = text,
            onValueChange = {
                text = it
                onAction(HomeScreenActions.OnSetText(text = it))
            },
            label = { Text("Label") },
            singleLine = true
        )

        TextButton(onClick = { onAction(HomeScreenActions.Send) }) {
            Text("Send")
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(
            viewModel = HomeScreenViewModel(),
            onAction = {}
        )
    }
}
