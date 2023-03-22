package fr.uge.plutus.data.repository

import fr.uge.plutus.data.dao.TagDao
import fr.uge.plutus.data.interfaces.ITagRepository
import fr.uge.plutus.data.model.Tag
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TagRepository @Inject constructor(
    private val tagDao: TagDao
) : ITagRepository {
    override fun retrieveAllTag(): Flow<List<Tag>> = tagDao.retrieveAll()

    override fun retrieveAllTag(type: String): Flow<List<Tag>> = tagDao.retrieveAll(type)

    override suspend fun createTag(tag: Tag): Long = tagDao.create(tag)

    override suspend fun retrieveTag(id: Long) = tagDao.retrieveById(id)

    override suspend fun updateTag(tag: Tag) = tagDao.update(tag)

    override suspend fun deleteTag(tag: Tag) = tagDao.delete(tag)
}