package com.nerazim.emtest.presentation

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nerazim.emtest.domain.AddToFavoritesUseCase
import com.nerazim.emtest.domain.GetOffersUseCase
import com.nerazim.emtest.domain.GetVacanciesUseCase
import com.nerazim.emtest.domain.RemoveFromFavoritesUseCase
import com.nerazim.emtest.presentation.app.jobApplication

object ViewModelFactory {
    val Factory = viewModelFactory {

        initializer {
            val getOffersUseCase = GetOffersUseCase(jobApplication().container.dataRepository)
            val getVacanciesUseCase = GetVacanciesUseCase(jobApplication().container.dataRepository)
            val addToFavoritesUseCase = AddToFavoritesUseCase(jobApplication().container.dataRepository)
            val removeFromFavoritesUseCase = RemoveFromFavoritesUseCase(jobApplication().container.dataRepository)
            SearchViewModel(
                getOffersUseCase = getOffersUseCase,
                getVacanciesUseCase = getVacanciesUseCase,
                addToFavoritesUseCase = addToFavoritesUseCase,
                removeFromFavoritesUseCase = removeFromFavoritesUseCase
            )
        }
    }
}