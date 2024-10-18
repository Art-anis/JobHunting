package com.nerazim.emtest.domain

import com.nerazim.emtest.data.Offer

class GetOffersUseCase(private val repository: DataRepository) {

    fun execute(): List<Offer> {
        return repository.getOffers()
    }
}