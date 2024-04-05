package com.example.propinasexamen.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.propinasexamen.activitys.Tip

@Dao
interface TipDao {
    @Query("SELECT * FROM tips")
    suspend fun getAllTips(): List<Tip>

    @Insert
    suspend fun insertTip(tip: Tip)

    @Delete
    suspend fun deleteTip(tip: Tip)
}
