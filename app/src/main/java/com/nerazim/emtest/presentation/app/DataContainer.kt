package com.nerazim.emtest.presentation.app

import com.nerazim.emtest.data.ApiHelper
import com.nerazim.emtest.data.DataRepositoryImpl
import com.nerazim.emtest.data.RetrofitBuilder
import com.nerazim.emtest.domain.DataRepository

interface Container {
    val dataRepository: DataRepository
}

class DataContainer: Container {
    override val dataRepository: DataRepository by lazy {
        DataRepositoryImpl(ApiHelper(RetrofitBuilder().dataApi))
    }
}