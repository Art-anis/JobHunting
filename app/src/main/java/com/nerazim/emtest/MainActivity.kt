package com.nerazim.emtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nerazim.emtest.ui.theme.EMTestTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://drive.usercontent.google.com/u/0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val dataApi = retrofit.create(DataApi::class.java)

        enableEdgeToEdge()
        setContent {
            EMTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val dataState = remember { mutableStateOf(Data()) }
                    LaunchedEffect(Unit) {
                        dataState.value = CoroutineScope(Dispatchers.IO).async {
                            dataApi.getData()
                        }.await()
                    }
                    Greeting(
                        name = if(dataState.value.vacancies.isEmpty()) "Android" else dataState.value.vacancies[0].title,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EMTestTheme {
        Greeting("Android")
    }
}