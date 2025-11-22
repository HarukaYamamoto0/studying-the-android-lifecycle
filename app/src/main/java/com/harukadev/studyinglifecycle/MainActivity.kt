package com.harukadev.studyinglifecycle

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.harukadev.studyinglifecycle.ui.theme.StudyingLifecycleTheme

class MainActivity : ComponentActivity() {

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
                        var text by remember { mutableStateOf("Initial value") }

                        Text("Saved Text: $text")

                        Spacer(Modifier.size(16.dp))

                        TextField(
                            value = text,
                            onValueChange = { text = it },
                            label = { Text("Label") },
                            singleLine = true
                        )
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
