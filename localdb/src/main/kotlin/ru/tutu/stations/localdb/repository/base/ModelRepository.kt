package ru.tutu.stations.localdb.repository.base

import ru.tutu.stations.localdb.model.base.ModelWithId

/**
 * Репозиторий для классической сущности локальной БД.
 *
 * @author Grigoriy Pryamov
 */
interface ModelRepository<Model, Id> : Repository where Model : ModelWithId<Id> {
    /**
     * Выполняет вставку [model] в БД
     * @param model Модель
     */
    fun insert(model: Model)

    /**
     * Выполняет вставку списка [models] в БД
     * @param models Список моделей
     */
    fun insert(models: List<Model>)

    /**
     * Выполняет обновление [model] в БД
     * @param model Модель
     */
    fun update(model: Model)

    /**
     * Удаляет [model] из БД
     * @param model Модель
     */
    fun delete(model: Model)

    /**
     * Удаляет список [models] из БД
     * @param models Список моделей
     */
    fun delete(models: List<Model>)

    /**
     * Возвращает все записи из таблицы БД
     */
    fun getAll(): List<Model>

    /**
     * Удаляет все записи из таблицы БД
     */
    fun deleteAll()

    /**
     * Возвращает запись по Id
     */
    fun getById(id: Id): Model?

    /**
     * Проверяет наличие записи по Id
     */
    fun exists(id: Id): Boolean
}
