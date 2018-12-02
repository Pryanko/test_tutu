package ru.tutu.stations.localdb.impl.base

import ru.tutu.stations.localdb.AppDatabase
import ru.tutu.stations.localdb.model.base.ModelWithId
import ru.tutu.stations.localdb.room.entity.base.EntityWithId

/**
 * Базовый репозиторий
 *
 * @author Grigoriy Pryamov
 */
abstract class ModelRepositoryWithLongId<Model : ModelWithId<Long>, Entity : EntityWithId<Long>>
protected constructor(appDatabase: AppDatabase) : ModelRepositoryImpl<Model, Entity, Long>(appDatabase) {

    override fun insert(model: Model) {
        val entity = mapper.modelToEntity(model)
        val id = dao.insert(entity!!)
        model.id = id
    }

    override fun insert(models: List<Model>) {
        val entities = mapper.modelListToEntityList(models)
        val ids = dao.insert(entities!!)
        for (i in models.indices) {
            models[i].id = ids[i]
        }
    }
}
