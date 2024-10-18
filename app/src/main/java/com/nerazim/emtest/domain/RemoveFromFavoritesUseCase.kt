package com.nerazim.emtest.domain

import com.nerazim.emtest.data.Vacancy

class RemoveFromFavoritesUseCase(private val repository: DataRepository) {

    fun execute(vacancy: Vacancy) {
        repository.getVacancies().find { it == vacancy }?.isFavorite = false
    }
}