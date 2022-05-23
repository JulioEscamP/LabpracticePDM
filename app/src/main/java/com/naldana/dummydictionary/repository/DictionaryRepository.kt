package com.naldana.dummydictionary.repository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.naldana.dummydictionary.data.model.DummyDictionaryDatabase
import com.naldana.dummydictionary.data.model.Word
import com.naldana.dummydictionary.data.model.dao
import com.naldana.dummydictionary.network.dto.ApiResponse
import com.naldana.dummydictionary.network.dto.WordService
import okio.IOException
import retrofit2.HttpException

class DictionaryRepository (
    database: DummyDictionaryDatabase,
    private val api:WordService,

) {
    private val wordDoa = database.wordDao()

    suspend fun getAllWords() : ApiResponse<LiveData<List<Word>>>{
        return try{
            val response = api.getAllword()
            if (response.count > 0) {
                wordDoa.insertWord(response.words)
            }
            ApiResponse.Success(data = wordDoa.getAllWords())
        } catch (e:HttpException){
            ApiResponse.Error(exception = e)
        } catch (e: IOException) {
            ApiResponse.Error(exception = e)
        }
    }

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