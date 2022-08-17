package com.minitiktok.android.logic.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.minitiktok.android.logic.model.Version

@Database(version = 1, entities = [Version::class])
abstract class VersionDatabase : RoomDatabase() {
    abstract fun VersionDao(): VersionDao

    companion object {
        private var instance: VersionDatabase? = null

        @Synchronized
        fun getInstance(context: Context): VersionDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                VersionDatabase::class.java,
                "versions_db"
            ).build().apply {
                instance = this
            }
        }
    }
}