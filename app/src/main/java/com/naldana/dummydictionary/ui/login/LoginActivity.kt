package com.naldana.dummydictionary.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.naldana.dummydictionary.databinding.ActivityLoginBinding
import com.naldana.dummydictionary.DummyDictionaryApplication
import com.naldana.dummydictionary.MainActivity
import com.naldana.dummydictionary.R
import com.naldana.dummydictionary.ui.ViewModelFactory
import com.naldana.dummydictionary.ui.word.WordViewModelFactory

class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    val app by lazy {
        application as DummyDictionaryApplication
    }
    private val viewModelFactory by lazy{
        ViewModelFactory(app.getDictionaryRepository())
    }
    private val viewModel: LoginViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        if(app.isUserLogin()) {
            return startMainActivity()
        }
        binding = DataBindingUtil.setContentView<>(this,R.layout.activity_login)
        binding.viewModel = viewModel

        viewModel.status.observe(this) { status ->
            handleUiState(status)
        }
    }

    private fun handleUiState(status: LoginUiStatus){
        when (status) {
            is LoginUiStatus.Error -> Log.d("Login Last Status", "Error")
            LoginUiStatus.Loading -> Log.d("Login List status","Loading")
            LoginUiStatus.Resume -> Log.d("Login List status","Resume")
            is LoginUiStatus.Success -> {
                app.saveAuthToken(status.token)
                startMainActivity()
            }
        }
    }

    private fun startMainActivity(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}