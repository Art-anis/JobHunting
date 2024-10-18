package com.nerazim.emtest.domain

import com.nerazim.emtest.data.Vacancy

class GetFavoritesUseCase(private val dataRepository: DataRepository) {
    fun execute(): List<Vacancy> {
        return dataRepository.getVacancies().filter { it.isFavorite == true }
    }
}