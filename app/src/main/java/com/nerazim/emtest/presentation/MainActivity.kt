package com.nerazim.emtest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.nerazim.emtest.DataApi
import com.nerazim.emtest.ui.theme.EMTestTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
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