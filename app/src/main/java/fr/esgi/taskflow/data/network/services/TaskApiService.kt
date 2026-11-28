package fr.esgi.taskflow.data.network.services

import fr.esgi.taskflow.data.network.TaskDto
import retrofit2.http.GET

// Retrofit implémente l'interface, pour fournir une implementation concrète à la fonciton getAllTodos()
// class TaskApiService(): TaskApiService {
/*
    override fun getAllTodos(): List<TaskDto> {
        // Code pour faire le get + construction de la requete HTT
    }

 */
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