package ru.tutu.stations.localdb.room.dao.base

import android.arch.persistence.db.SupportSQLiteQuery
import android.arch.persistence.room.*

/**
 * Базовый Dao
 *
 * @author Grigoriy Pryamov
 */
interface BaseDao<Entity> {
    /**
     * Вставляет [entity] в БД
     * @param entity Сущность
     */
    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(entity: Entity): Long

    /**
     * Вставляет список [entities] в БД
     * @param entities Список сущностей
     */
    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(entities: List<Entity>): LongArray

    /**
     * Обновляет [entity] в БД
     * @param entity Сущность
     */
    @Update(onConflict = OnConflictStrategy.FAIL)
    fun update(entity: Entity)

    /**
     * Удаляет [entity] из БД
     * @param entity Сущность
     */
    @Delete
    fun delete(entity: Entity)

    /**
     * Удаляет список [entities] из БД
     * @param entities Список сущностей
     */
    @Delete
    fun delete(entities: List<Entity>)

    /**
     * Возвращает список записей из таблицы БД
     */
    @RawQuery
    fun getList(query: SupportSQLiteQuery): List<Entity>

    /**
     * Возвращает запись из таблицы БД
     */
    @RawQuery
    fun getSingle(query: SupportSQLiteQuery): Entity?

    /**
     * Возвращает булево значение из запроса к БД
     */
    @RawQuery
    fun getBoolean(query: SupportSQLiteQuery): Boolean
}
