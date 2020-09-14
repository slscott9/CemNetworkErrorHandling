package com.example.networkerrorhandling.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.networkerrorhandling.data.entities.Cemetery
import com.example.networkerrorhandling.data.entities.Grave

@Database(entities = [Cemetery::class, Grave::class], version = 2, exportSchema = false)
abstract class CemeteryRoomDatabase : RoomDatabase() {

    abstract fun cemDao(): CemeteryDao

    companion object {
        @Volatile
        private var instance: CemeteryRoomDatabase? = null


        fun getDatabase(context: Context): CemeteryRoomDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }


        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(
                appContext,
                CemeteryRoomDatabase::class.java,
                "error_cemetery_database"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}



