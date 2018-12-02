package ru.tutu.stations.localdb.impl.base

import ru.tutu.stations.localdb.AppDatabase

/**
 * Репозиторий
 *
 * @author Grigoriy Pryamov
 */
abstract class RepositoryImpl internal constructor(protected var appDatabase: AppDatabase)
