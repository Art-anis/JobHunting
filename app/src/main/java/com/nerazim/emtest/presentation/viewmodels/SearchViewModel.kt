package com.nerazim.emtest.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nerazim.emtest.data.Offer
import com.nerazim.emtest.data.Vacancy
import com.nerazim.emtest.domain.AddToFavoritesUseCase
import com.nerazim.emtest.domain.GetOffersUseCase
import com.nerazim.emtest.domain.GetVacanciesUseCase
import com.nerazim.emtest.domain.RemoveFromFavoritesUseCase

class SearchViewModel(
    private val getOffersUseCase: GetOffersUseCase,
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
): ViewModel() {

    private val offersLiveMutable = MutableLiveData<List<Offer>>()
    private val offersLive: LiveData<List<Offer>>
        get() = offersLiveMutable

    private fun refreshOffers() {
        val newList = getOffersUseCase.execute()
        offersLiveMutable.value = newList.map { it.copy() }
    }

    private fun refreshVacancies() {
        val newList = getVacanciesUseCase.execute()
        vacanciesLiveMutable.value = newList.map { it.copy() }
    }

    fun getOfferListLive(): LiveData<List<Offer>> {
        refreshOffers()
        return offersLive
    }

    private val vacanciesLiveMutable = MutableLiveData<List<Vacancy>>()
    private val vacanciesLive: LiveData<List<Vacancy>>
        get() = vacanciesLiveMutable

    fun getVacancyListLive(): LiveData<List<Vacancy>> {
        refreshVacancies()
        return vacanciesLive
    }

    fun addToFavorites(vacancy: Vacancy) {
        addToFavoritesUseCase.execute(vacancy)
        refreshVacancies()
        //bbvm.refreshFavoriteNumber()
    }

    fun removeFromFavorites(vacancy: Vacancy) {
        removeFromFavoritesUseCase.execute(vacancy)
        refreshVacancies()
        //bbvm.refreshFavoriteNumber()
    }
}