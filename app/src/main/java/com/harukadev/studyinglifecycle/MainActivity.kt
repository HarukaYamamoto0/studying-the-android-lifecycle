package com.harukadev.studyinglifecycle

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.harukadev.studyinglifecycle.ui.theme.StudyingLifecycleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContent {
//            StudyingLifecycleTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Column(
//                        modifier = Modifier
//                            .padding(innerPadding)
//                            .fillMaxSize()
//                            .background(MaterialTheme.colorScheme.background)
//                    ) {
//                        var name by remember { mutableStateOf("Hello ") }
//
//                        Text(
//                            text = "Hello!"
//                        )
//                        TextField(
//                            value = name,
//                            onValueChange = { name = it },
//                            label = { Text("Label") },
//                            singleLine = true
//                        )
//
//                        Spacer(Modifier.size(width = 0.dp, height = 25.dp))
//
//                        TextButton(onClick = {
////                            val sendIntent = Intent().apply {
////                                action = Intent.ACTION_SEND
////                                type = "text/plain"
////                                putExtra(Intent.EXTRA_TEXT, name)
////                            }
////                            startActivity(this@MainActivity, sendIntent, null)
//                            val intent =
//                                Intent(this@MainActivity, ReceiverActivity::class.java).apply {
//                                    putExtra("text", name)
//                                }
//
//                            startActivity(intent)
//
//                        }) {
//                            Text("Send Email")
//                        }
//                    }
//                }
//            }
//        }
    }
}