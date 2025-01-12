package com.example.binchecker.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.binchecker.data.storage.model.CardInfoEntity

@Dao
interface CardInfoDao {

    @Insert
    fun insertCardInfoToDatabase(cardInfoEntity: CardInfoEntity)

    @Query("SELECT * FROM card_info_table")
    fun getRequestCardInfoHistory(): List<CardInfoEntity>
}