package com.naldana.dummydictionary.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

class DummyDictionaryDatabase {
    @Database(
        entities = [Word::class, Antonym::class, Synonym::class],
        version = 1,
        exportSchema = false
    )
    abstract class DummyDictionaryDatabase : RoomDatabase() {
        abstract fun wordDao(): dao.WordDao
        abstract fun synonymDao(): dao.SynonymDao
        abstract fun antonymDao(): dao.AntonymDao
    }

    companion object {
        @Volatile
        private var INSTANCE: DummyDictionaryDatabase? = null

        fun getInstance(context: Context): DummyDictionaryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DummyDictionaryDatabase::class.java,
                    "dic_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}

