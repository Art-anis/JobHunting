package com.nerazim.emtest.domain

import android.content.Context
import com.nerazim.emtest.data.Data
import com.nerazim.emtest.data.Offer
import com.nerazim.emtest.data.Vacancy
import kotlinx.coroutines.CoroutineScope

//репозиторий, в котором будут храниться данные
interface DataRepository {

    //загрузка данных в виде JSON-файла и его десериализация
    suspend fun getData(context: Context): Data

    //выделение списка рекомендаций
    fun getOffers(): List<Offer>

    //выделение списка вакансий
    fun getVacancies(): List<Vacancy>
}