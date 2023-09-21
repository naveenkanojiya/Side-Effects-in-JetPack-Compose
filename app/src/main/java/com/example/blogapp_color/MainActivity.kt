package com.example.blogapp_color

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.tooling.preview.Preview
import com.example.blogapp_color.ui.theme.BlogAppColorTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //  App()
            //Count()
            // LaunchEffectComposable()
            //CoroutineScopeComposable()
        }
    }
}

@Composable
fun App() {
    var theme = remember { mutableStateOf(false) }
    BlogAppColorTheme(theme.value) {

        Column(
            Modifier.background(MaterialTheme.colorScheme.background),
        ) {
            Text(
                text = "Code With Me",
                style = MaterialTheme.typography.bodySmall
            )
            Button(onClick = {
                theme.value = !theme.value
            }) {
                Text(text = "Change Theme")
            }
        }


        @Composable
        fun Count() {
            var count = remember { mutableStateOf(0) }
            var key = count.value % 3 == 0
            LaunchedEffect(key1 = key) {
                Log.d("Counter", "Current Count : ${count.value}")
            }
            Button(onClick = { count.value++ }) {
                Text(text = "Increment Count")

            }

        }

        @Composable
        fun LaunchEffectComposable() {
            val counter = remember { mutableStateOf(0) }

            LaunchedEffect(key1 = Unit) {
                Log.d("LaunchEffectComposablr", "Started...")
                try {
                    for (i in 1..10) {
                        counter.value++
                        delay(1000)
                    }
                } catch (e: Exception) {
                    Log.d("LaunchEffectComposable", "Exception - ${e.message.toString()}")
                }
            }

            var text = "Counter is runing ${counter.value}"
            if (counter.value == 10) {
                text = "Counter Stopped"
            }
            Text(text = text)

        }

        @Composable
        fun CoroutineScopeComposable() {
            val counter = remember { mutableStateOf(0) }
            var scope = rememberCoroutineScope()

            var text = "Counter is runing ${counter.value}"
            if (counter.value == 10) {
                text = "Counter stopped"
            }
            Column {
                Text(text = text)
                Button(onClick = {

                    scope.launch {
                        Log.d("CoroutineScopeComposable", "Started")
                        try {
                            for (i in 1..10) {
                                counter.value++
                                delay(1000)
                            }
                        } catch (e: Exception) {
                            Log.d("CoroutineScopeComposable", "Started...")
                        }

                    }

                }) {
                    Text(text = "Start")
                }
            }
        }
    }
}