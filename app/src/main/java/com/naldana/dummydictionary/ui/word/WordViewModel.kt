package com.naldana.dummydictionary.ui.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naldana.dummydictionary.data.model.Word
import com.naldana.dummydictionary.network.dto.ApiResponse
import com.naldana.dummydictionary.repository.DictionaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class WordViewModel(private val repository: DictionaryRepository): ViewModel() {
    private val _status = MutableLiveData<WordUiState>(WordUiState.Loading)
    val status: LiveData<WordUiState>
    get()=_status


    /*val words = repository.getAllWords()*/

    fun getAllWords(){
        _status.value = WordUiState.Loading
        viewModelScope.launch(Dispatchers.IO){
            _status.postValue(
                when (val response = repository.getAllWords()){
                    is ApiResponse.Error -> WordUiState.Error(response.exception)
                    is ApiResponse.Success -> WordUiState.Success(response.data)
                    is ApiResponse.ErrorWithMessage -> TODO()
                }
            )
        }
    }

    fun addword(word: Word){
        viewModelScope.launch{
            repository.addWord(word)
        }
    }
    sealed class WordUiState(){
        object Loading : WordUiState()
        class Error(val exception: Exception) : WordUiState()
        data class Success(val word: LiveData<List<Word>>) : WordUiState()
    }
}