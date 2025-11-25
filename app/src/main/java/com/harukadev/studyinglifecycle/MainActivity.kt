package com.harukadev.studyinglifecycle

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.harukadev.studyinglifecycle.homescreen.HomeScreen
import com.harukadev.studyinglifecycle.homescreen.HomeScreenViewModel
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
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel,
                        onAction = viewModel::onAction
                    )
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
