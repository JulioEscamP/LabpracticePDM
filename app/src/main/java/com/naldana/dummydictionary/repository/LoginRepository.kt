package com.naldana.dummydictionary.repository

import com.naldana.dummydictionary.network.dto.ApiResponse
import com.naldana.dummydictionary.network.dto.LoginRequest
import com.naldana.dummydictionary.network.dto.WordService
import okio.IOException
import retrofit2.HttpException

class LoginRepository(private val api: WordService) {

    suspend fun getloginRepository(username: String, password: String): ApiResponse<String>{
        try{
            val response = api.login(LoginRequest(username,password))
            return ApiResponse.Success(response.token)

        } catch (e: HttpException){
            if(e.code() == 400){
                return ApiResponse.ErrorWithMessage(e.response()?.body().toString())
            }
            return ApiResponse.Error(e)
        } catch (e: IOException){
            return ApiResponse.Error(e)
        }
    }
}