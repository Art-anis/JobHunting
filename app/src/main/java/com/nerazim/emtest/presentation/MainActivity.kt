package com.nerazim.emtest.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.nerazim.emtest.data.ApiHelper
import com.nerazim.emtest.data.DataRepositoryImpl
import com.nerazim.emtest.data.RetrofitBuilder
import com.nerazim.emtest.domain.DataRepository
import com.nerazim.emtest.ui.theme.EMTestTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val dataApi = RetrofitBuilder().dataApi
        val apiHelper = ApiHelper(dataApi)
        val repository = DataRepositoryImpl(apiHelper)
        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getData(applicationContext)
            Log.d("data", data.toString())
        }

        setContent {
            EMTestTheme {
                //переменная состояния, отслеживающая текущий экран
                var currentScreen: BottomBarDestination by remember { mutableStateOf(BottomBarDestination.Search) }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    //нижний бар - навигация
                    bottomBar = {
                        //список всех возможных экранов
                        val screens = listOf(
                            BottomBarDestination.Search,
                            BottomBarDestination.Favorite,
                            BottomBarDestination.Replies,
                            BottomBarDestination.Messages,
                            BottomBarDestination.Profile
                        )
                        //сам бар
                        JobBottomBar(
                            screens = screens,
                            onTabSelected = { screen -> currentScreen = screen},
                            currentScreen = currentScreen
                        )
                    }
                //содержимое, пока пустое
                ) { innerPadding ->
                    Greeting(
                        name = "Android",
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