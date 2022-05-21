package com.naldana.dummydictionary.network.dto

class LoginRequest {
    data class LoginRequest(

        val username: String,
        val password: String
    )
}