package fr.esgi.taskflow.data.network.services

import fr.esgi.taskflow.data.network.dto.UserDto
import retrofit2.http.GET

interface UserApiService {

    @GET("users")
    suspend fun getAllUsers(): List<UserDto>
}