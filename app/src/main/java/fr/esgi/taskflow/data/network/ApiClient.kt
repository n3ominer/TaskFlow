package fr.esgi.taskflow.data.network

import fr.esgi.taskflow.data.network.services.TaskApiService
import fr.esgi.taskflow.data.network.services.UserApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) // Map JSON -> Data class
        .build()

    fun createTaskApiService(): TaskApiService =
        retrofit.create(TaskApiService::class.java)

    fun createUserApiService(): UserApiService =
        retrofit.create(UserApiService::class.java)
}