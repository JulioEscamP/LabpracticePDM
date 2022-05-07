package com.naldana.dummydictionary

import android.app.Application
import com.naldana.dummydictionary.data.model.DummyDictionaryDatabase

class DummyDictionaryApplication {
    class DummyDictionaryApplication : Application() {
        val dataBase by lazy {
            DummyDictionaryDatabase.getInstance(this)
        }
    }
    fun getDictionaryRepository() = with(dataBase) {
        DictionaryRepository(wordDao(), synonymDao(), antonymDao())
    }
}