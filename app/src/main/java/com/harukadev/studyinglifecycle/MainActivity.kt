package com.harukadev.studyinglifecycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.harukadev.studyinglifecycle.ui.theme.StudyingLifecycleTheme

class MainActivity : ComponentActivity() {
    val viewModel: HomeScreenViewModel by viewModels()

    private fun log(stage: String) {
        Log.d("LifecycleStage", stage)
    }

    // Called when the Activity is first created.
    // Use this for initial setup: state, dependencies, UI configuration.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("onCreate")
        enableEdgeToEdge()

        setContent {
            StudyingLifecycleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val state by viewModel.uiState.collectAsStateWithLifecycle()
                        var text by remember { mutableStateOf(state.text) }

                        Text("Saved Text: $text")

                        Spacer(Modifier.size(16.dp))

                        TextField(
                            value = text,
                            onValueChange = { text = it; viewModel.setText(it) },
                            label = { Text("Label") },
                            singleLine = true
                        )

                        TextButton(
                            onClick = {
                                val sendIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    type = "text/plain"
                                    putExtra(Intent.EXTRA_TEXT, text)
                                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                            Intent.FLAG_ACTIVITY_NEW_TASK
                                }
                                startActivity(sendIntent)
                            }
                        ) {
                            Text("Send")
                        }
                    }
                }
            }
        }
    }

    // Activity becomes visible but is not yet interactive.
    override fun onStart() {
        super.onStart()
        log("onStart")
    }

    // Activity is now in the foreground and fully interactive.
    override fun onResume() {
        super.onResume()
        log("onResume")
    }

    // Activity is about to lose focus.
    // Good place to pause animations or persist lightweight state.
    override fun onPause() {
        super.onPause()
        log("onPause")
    }

    // Activity is no longer visible to the user.
    override fun onStop() {
        super.onStop()
        log("onStop")
    }

    // Activity is coming back after being stopped.
    override fun onRestart() {
        super.onRestart()
        log("onRestart")
    }

    // Called before the Activity is destroyed.
    // Release resources, unregister listeners, cleanup.
    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }
}
