package com.naldana.dummydictionary.network.dto

import retrofit2.http.GET
import retrofit2.http.POST

interface WordService {
    @GET("/words")
    suspend fun getAllword():WordsResponse

    @POST("/login")
    suspend fun login(@BODY credentials: LoginRequest): LoginResponse
}