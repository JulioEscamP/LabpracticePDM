package com.naldana.dummydictionary.ui.word

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naldana.dummydictionary.data.model.Word
import com.naldana.dummydictionary.repository.DictionaryRepository
import kotlinx.coroutines.launch

class WordViewModel(private val repository: DictionaryRepository): ViewModel() {
    val words = repository.getAllWords()

    fun addword(word: Word){
        viewModelScope.launch{
            repository.addWord(word)
        }
    }
}