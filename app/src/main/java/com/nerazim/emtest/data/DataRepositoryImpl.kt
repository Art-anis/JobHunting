package com.nerazim.emtest.data

import android.content.Context
import com.google.gson.Gson
import com.nerazim.emtest.domain.DataRepository
import kotlinx.coroutines.coroutineScope

class DataRepositoryImpl(private val apiHelper: ApiHelper): DataRepository {

    override suspend fun getData(context: Context): Data {
        val data: Data = coroutineScope {
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
        return data
    }

    override fun getOffers(): List<Offer> {
        TODO("Not yet implemented")
    }

    override fun getVacancies(): List<Vacancy> {
        TODO("Not yet implemented")
    }

}