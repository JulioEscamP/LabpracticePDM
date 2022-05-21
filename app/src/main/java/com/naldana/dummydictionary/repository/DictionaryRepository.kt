package com.naldana.dummydictionary.repository
import androidx.lifecycle.MutableLiveData
import com.naldana.dummydictionary.data.model.Word
import com.naldana.dummydictionary.data.model.dao

class DictionaryRepository (

    private val wordDoa: dao.WordDao,
    val synonymDao: dao.SynonymDao,
    val antonymDao: dao.AntonymDao
) {
    fun getAllWords() = wordDoa.getAllWords()

    suspend fun addWord(word: Word) {
        wordDoa.insertWord(word)
    }

    private var _words = listOf(
        Word("condimentum.","rutrum eu, ultrices sit amet, risus. Donec nibh enim, gravida sit amet, dapibus id, " +
                "blandit at, nisi. Cum sociis natoque penatibus et magnis dis parturient montes, " +
                "nascetur ridiculus mus. Proin vel nisl. Quisque fringilla euismod","1", isFav = false)

    ).toMutableList()

    val words: MutableLiveData<List<Word>>
        get() = MutableLiveData(_words)

    /* fun addWord(word: Word) {
        _words.add(word)
        words.value = _words

     */
    }