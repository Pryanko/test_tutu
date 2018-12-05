package ru.tutu.stations.data

/**
 * @author Grigoriy Pryamov.
 */
sealed class SyncStatus

/**
 * Статус загрузки
 */
object DownloadStatus : SyncStatus()

/**
 * Статус неизвестен
 */
object UnknownStatus : SyncStatus()

/**
 * Статус обработки (запись в бд)
 */
object ProcessStatus : SyncStatus()

/**
 * Успешная синхронизация
 */
object SuccessStatus : SyncStatus()

/**
 * Не требуется синхронизация
 */
object NotRequireStatus : SyncStatus()

/**
 * Ошибка - требуется повторить
 */
object ErrorStatus : SyncStatus()

/**
 * Отложенная синхронизаця (но можно продолжать)
 */
object DeferredStatus : SyncStatus()
