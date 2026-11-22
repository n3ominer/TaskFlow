package fr.esgi.taskflow.data.network

import retrofit2.http.GET

interface TaskApiService {

    // GET ALL TASK ENDPOINT
    @GET("todos")
    suspend fun getAllTodos(): List<TaskDto>


    // GET ONE TASK BY ID ENDPOINT


    // DELETE ONE TASK ENDPOINT


    // DELETE ALL TASKS ENDPOINT


    // UPDATE COMPLETED STATE ENDPOINT
    //....
}
