package com.example.networkerrorhandling.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.networkerrorhandling.data.entities.Cemetery


@Dao
interface CemeteryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCemsFromNetwork(vararg cemetery: Cemetery)

    @Query("select * from error_cemetery_table")
    fun getAllCemeteries(): LiveData<List<Cemetery>>

    @Query("select * from error_cemetery_table where cemeteryRowId= :cemeteryId")
    fun getCemeteryWithId(cemeteryId: Int): LiveData<Cemetery>
}