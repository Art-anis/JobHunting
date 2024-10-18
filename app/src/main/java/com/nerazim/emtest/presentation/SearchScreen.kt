package com.nerazim.emtest.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nerazim.emtest.R

@Composable
fun SearchScreen() {
    val viewModel: SearchViewModel = viewModel(factory = ViewModelFactory.Factory)

    val offers by viewModel.getOfferListLive().observeAsState(listOf())
    val vacancies by viewModel.getVacancyListLive().observeAsState(listOf())

    Surface(Modifier.fillMaxSize()) {
        Column {
            Row(Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                var search by remember { mutableStateOf("") }
                TextField(
                    value = search,
                    onValueChange = { value -> search = value },
                    placeholder = {
                        Text("Должность, ключевые слова")
                    },
                    leadingIcon = {
                        Image(painterResource(R.drawable.search_inactive), contentDescription = null)
                    }
                )

                Button(
                    onClick = {}
                ) {
                    Image(painter = painterResource(R.drawable.filter), contentDescription = null)
                }
            }
            Spacer(Modifier.height(16.dp))

            LazyRow(
                Modifier.padding(start = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(offers) { offer ->
                    OfferComponent(offer)
                }
            }

            Spacer(Modifier.height(32.dp))

            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(vacancies) { vacancy ->
                    VacancyComponent(
                        vacancy = vacancy,
                        addToFavorites = viewModel::addToFavorites,
                        removeFromFavorites = viewModel::removeFromFavorites
                    )
                }
            }
        }
    }
}