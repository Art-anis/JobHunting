package com.nerazim.emtest.domain

import com.nerazim.emtest.data.Vacancy

class GetVacanciesUseCase(private val repository: DataRepository) {

    fun execute(): List<Vacancy> {
        return repository.getVacancies()
    }
}