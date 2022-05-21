package com.naldana.dummydictionary.ui.login

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.naldana.dummydictionary.DummyDictionaryApplication
import com.naldana.dummydictionary.R
import com.naldana.dummydictionary.ui.word.WordViewModelFactory

class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    val app by lazy {
        application as DummyDictionaryApplication
    }
    private val viewModelFactory by lazy{
        WordViewModelFactory(app.getDictionaryRepository())
    }
    private val viewModel: LoginViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?){
        Super.onCreate(savedInstanceState)
        if(app.isUserLogin()){
            return startMainActivity()
        }
        /*binding = DataBindingUtil.setContentView(this, R.layout.activity)*/
    }

    private fun handleUiState(status: LoginStatus){
        when (status) {
            is LoginUiStatus.Error -> Log.d("Login Last Status")
            LoginUistatus.Loading -> Log.d("Login List status")
            LginUiStatus.Resume -> Log.d("Login List status")
            is LoginUiStatus.Success -> {
                app.saveAuthToken(status.token)
                startMainActivity()
            }
        }
    }

}