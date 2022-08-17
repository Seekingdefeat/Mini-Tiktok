package com.minitiktok.android.logic.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.minitiktok.android.logic.model.Version

@Dao
interface VersionDao {
    @Insert
    fun insertVersion(version: Version): Long

    @Transaction
    fun insertVersions(versions: List<Version>, now: Long, count: Long, cursor: Long) {
        for (version in versions) {
            version.createTime = now
            version.count = count
            version.cursor = cursor
            insertVersion(version);
        }
    }

    @Query("select * from Version order by version desc")
    fun getAllVersions(): List<Version>
}