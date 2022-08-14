package com.minitiktok.android.logic.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import com.minitiktok.android.logic.model.ClientToken
import androidx.room.Query

@Dao
interface ClientTokenDao {
    @Insert
    fun insertToken(token: ClientToken): Long

    @Update
    fun updateToken(token: ClientToken)

    @Query("select * from ClientToken where client_key = :client_key")
    fun getTokenByKey(client_key: String): List<ClientToken>

}