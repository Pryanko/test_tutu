package ru.tutu.stations.localdb.impl.base

import android.arch.persistence.db.SimpleSQLiteQuery
import ru.tutu.stations.localdb.room.mapper.base.BaseMapper
import ru.tutu.stations.localdb.AppDatabase
import ru.tutu.stations.localdb.model.base.ModelWithId
import ru.tutu.stations.localdb.repository.base.ModelRepository
import ru.tutu.stations.localdb.room.dao.base.BaseDao
import ru.tutu.stations.localdb.room.entity.base.EntityWithId

/**
 * Базовый репозиторий.
 *
 * @author Grigoriy Pryamov
 */
abstract class ModelRepositoryImpl<Model : ModelWithId<Id>, Entity : EntityWithId<Id>, Id>
protected constructor(appDatabase: AppDatabase) : RepositoryImpl(appDatabase), ModelRepository<Model, Id> {

    abstract val dao: BaseDao<Entity>

    protected val tableName by lazy(LazyThreadSafetyMode.NONE) {
        val className = dao::class.java.simpleName
        className.substring(0, className.length - "Dao_Impl".length)
    }

    open val idColumnName = "id"

    abstract val mapper: BaseMapper<Model, Entity>

    override fun insert(model: Model) {
        val entity = mapper.modelToEntity(model)
        dao.insert(entity!!)
    }

    override fun insert(models: List<Model>) {
        val entities = mapper.modelListToEntityList(models)
        dao.insert(entities!!)
    }

    override fun update(model: Model) {
        dao.update(mapper.modelToEntity(model)!!)
    }

    override fun delete(model: Model) {
        dao.delete(mapper.modelToEntity(model)!!)
    }

    override fun delete(models: List<Model>) {
        val entities = mapper.modelListToEntityList(models)
        dao.delete(entities!!)
    }

    override fun getAll(): List<Model> {
        val query = SimpleSQLiteQuery("SELECT * FROM $tableName")
        val entities = dao.getList(query)
        return mapper.entityListToModelList(entities)!!
    }

    override fun deleteAll() {
        appDatabase.openHelper.writableDatabase.execSQL("DELETE FROM $tableName")
    }

    override fun getById(id: Id): Model? {
        val query = SimpleSQLiteQuery("SELECT * FROM $tableName WHERE $idColumnName = ?", arrayOf(id as Any))
        val entity = dao.getSingle(query)
        return mapper.entityToModel(entity)
    }

    override fun exists(id: Id): Boolean {
        val query =
            SimpleSQLiteQuery("SELECT EXISTS(SELECT * FROM $tableName WHERE $idColumnName = ?)", arrayOf(id as Any))
        return dao.getBoolean(query)
    }
}
