package fr.esgi.taskflow.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiClient {
    const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun createTaskApiService(): TaskApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) // Map JSON -> Data class
        .build()
        .create(TaskApiService::class.java)


}