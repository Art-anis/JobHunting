package com.nerazim.emtest.data

import android.content.Context
import com.google.gson.Gson
import com.nerazim.emtest.domain.DataRepository
import kotlinx.coroutines.coroutineScope

class DataRepositoryImpl(private val apiHelper: ApiHelper): DataRepository {

    var data: Data = Data()

    override suspend fun loadData(context: Context) {
        data = coroutineScope {
            //пытаемся загрузить данные из API
            try {
                apiHelper.getData()
            }
            //если не получается, берем данные из локального файла
            catch (_: Exception) {
                val json: String = context.assets.open("мок json.json").bufferedReader().use { it.readText() }
                Gson().fromJson(json, Data::class.java)
            }
        }
    }

    override fun getOffers(): List<Offer> {
        return data.offers
    }

    override fun getVacancies(): List<Vacancy> {
        return data.vacancies
    }

    override fun getFavorites(): List<Vacancy> {
        return data.vacancies.filter { it.isFavorite == true }
    }

    override fun getFavoritesNumber(): Int {
        data.favoriteNumber = getFavorites().size
        return data.favoriteNumber
    }

    override fun addFavorite(vacancy: Vacancy) {
        data.vacancies.find { it == vacancy }?.isFavorite = true
        data.favoriteNumber += 1
    }

    override fun removeFavorite(vacancy: Vacancy) {
        data.vacancies.find { it == vacancy }?.isFavorite = false
        data.favoriteNumber -= 1
    }

}