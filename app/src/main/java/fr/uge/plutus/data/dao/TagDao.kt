package fr.uge.plutus.data.dao

import androidx.room.*
import fr.uge.plutus.data.model.Tag
import kotlinx.coroutines.flow.Flow


@Dao
interface TagDao {

    @Query("SELECT * FROM `Tag`")
    fun retrieveAll(): Flow<List<Tag>>

    @Query("SELECT * FROM `Tag` WHERE type=:type")
    fun retrieveAll(type: String): Flow<List<Tag>>

    @Query("SELECT * FROM `Tag` WHERE tagId=:id")
    suspend fun retrieveById(id: Long): Tag?

    @Insert
    suspend fun create(tag: Tag): Long

    @Update
    suspend fun update(tag: Tag): Int

    @Delete
    suspend fun delete(tag: Tag): Int

}