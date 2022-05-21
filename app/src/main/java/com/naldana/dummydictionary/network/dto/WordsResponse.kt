package com.naldana.dummydictionary.network.dto

import com.google.gson.annotations.SerializedName

data class WordsResponse (
    @SerializedName("count")
    val count:Int,
    @SerializedName("words")
    val words:List<Word>
)
