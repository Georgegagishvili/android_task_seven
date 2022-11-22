package com.example.taskseven.api.services

import com.example.taskseven.models.ReqResData
import com.example.taskseven.models.User
import retrofit2.Call
import retrofit2.http.*

interface ReqResService {
    @GET("users")
    fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<ReqResData<List<User>>>

    @GET("users/{id}")
    fun getUser(@Path("id") id: Long): Call<ReqResData<User>>
}