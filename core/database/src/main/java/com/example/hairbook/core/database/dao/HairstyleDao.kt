package com.example.hairbook.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.hairbook.core.database.entity.HairstyleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HairstyleDao {

    @Query("SELECT * FROM hairstyles ORDER BY sort_order ASC")
    fun observeAll(): Flow<List<HairstyleEntity>>

    @Query("SELECT * FROM hairstyles WHERE gender = :gender ORDER BY sort_order ASC")
    fun observeByGender(gender: String): Flow<List<HairstyleEntity>>

    @Query("SELECT * FROM hairstyles WHERE id = :id")
    fun observeById(id: String): Flow<HairstyleEntity?>

    @Query("SELECT COUNT(*) FROM hairstyles")
    suspend fun countAll(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(styles: List<HairstyleEntity>)

    @Upsert
    suspend fun upsert(style: HairstyleEntity)

    @Delete
    suspend fun delete(style: HairstyleEntity)
}
