package fr.uge.plutus.data.interfaces

import fr.uge.plutus.data.model.Tag
import kotlinx.coroutines.flow.Flow

interface ITagRepository {
    fun retrieveAllTag(): Flow<List<Tag>>

    fun retrieveAllTag(type: String): Flow<List<Tag>>

    suspend fun createTag(tag: Tag)

    suspend fun retrieveTag(id: Int): Tag?

    suspend fun updateTag(tag: Tag): Int

    suspend fun deleteTag(tag: Tag): Int
}