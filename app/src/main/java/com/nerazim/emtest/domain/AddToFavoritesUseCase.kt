package com.nerazim.emtest.domain

import com.nerazim.emtest.data.Vacancy

class AddToFavoritesUseCase(private val repository: DataRepository) {

    fun execute(vacancy: Vacancy) {
        repository.addFavorite(vacancy)
    }
}