package com.naldana.dummydictionary

import android.app.Application
import com.naldana.dummydictionary.data.model.DummyDictionaryDatabase
import com.naldana.dummydictionary.repository.DictionaryRepository

class DummyDictionaryApplication : Application() {
    val dataBase by lazy {
        DummyDictionaryDatabase.getInstance(this)
    }

    fun getDictionaryRepository() = with(dataBase) {
        DictionaryRepository(wordDao(), synonymDao(), antonymDao())
    }

}
