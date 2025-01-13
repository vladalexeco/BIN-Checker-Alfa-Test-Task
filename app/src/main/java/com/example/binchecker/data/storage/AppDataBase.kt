package com.example.binchecker.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.binchecker.data.storage.dao.CardInfoDao
import com.example.binchecker.data.storage.model.CardInfoEntity

@Database(version = 1, entities = [CardInfoEntity::class])
abstract class AppDataBase : RoomDatabase() {
    abstract fun getCardInfoDao(): CardInfoDao
}