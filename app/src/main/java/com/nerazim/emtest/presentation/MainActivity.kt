package com.nerazim.emtest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nerazim.emtest.ui.theme.EMTestTheme

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
                    Box(Modifier.padding(innerPadding).fillMaxSize()) {
                        currentScreen.screen()
                    }
                }
            }
        }
    }
}