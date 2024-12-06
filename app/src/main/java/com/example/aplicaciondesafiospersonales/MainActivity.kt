package com.example.aplicaciondesafiospersonales

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.aplicaciondesafiospersonales.challenges.domain.model.Challenge
import com.example.aplicaciondesafiospersonales.challenges.presentation.home_screen.HomeScreen
import com.example.aplicaciondesafiospersonales.ui.theme.AplicacionDesafiosPersonalesTheme
import com.example.aplicaciondesafiospersonales.util.Event
import com.example.aplicaciondesafiospersonales.util.EventBus
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val query = ParseQuery.getQuery<ParseObject>("Desafio")
        query.findInBackground { results: List<ParseObject>, e: ParseException? ->
            if (e == null) {
                for (result in results) {
                    result
                }
            }
        }

        setContent {
            val lifecycleOwner = LocalLifecycleOwner.current.lifecycle
            LaunchedEffect(key1 = lifecycleOwner) {
                lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    EventBus.events.collect { event ->
                        when (event) {
                            is Event.Toast -> {
                                Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                }
            }
            AplicacionDesafiosPersonalesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AplicacionDesafiosPersonalesTheme {
        Column {
            Text("Mis desafíos")

        }
    }
}