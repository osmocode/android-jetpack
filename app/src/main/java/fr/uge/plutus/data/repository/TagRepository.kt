package fr.uge.plutus.data.repository

import fr.uge.plutus.data.dao.TagDao
import fr.uge.plutus.data.interfaces.ITagRepository
import fr.uge.plutus.data.model.Tag
import javax.inject.Inject

class TagRepository @Inject constructor(
    private val tagDao: TagDao
) : ITagRepository {
    override fun retrieveAllTag() = tagDao.retrieveAll()

    override suspend fun createTag(tag: Tag) = tagDao.create(tag)

    override suspend fun retrieveTag(id: Int) = tagDao.retrieveById(id)

    override suspend fun updateTag(tag: Tag) = tagDao.update(tag)

    override suspend fun deleteTag(tag: Tag) = tagDao.delete(tag)
}