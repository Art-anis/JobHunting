package com.nerazim.emtest.data

class ApiHelper(private val dataApi: DataApi) {
    suspend fun getData() = dataApi.getData()
}