package ru.tutu.stations

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import ru.digipeople.database.sqlitestudioservice.SQLiteStudioService
import ru.digipeople.logger.LoggerFactory
import ru.tutu.stations.di.Dagger

/**
 * Базовый класс для [ContentProvider],
 * отвечающего за инициализацию приложения.
 *
 * @author Grigoriy Pryamov.
 */
class AppInitProvider : ContentProvider() {

    private val logger = LoggerFactory.getLogger(AppInitProvider::class)

    override fun onCreate(): Boolean {
        logger.trace("onCreate")
        initDi(context)
        initExternalSQLiteTool(context)
        return false
    }

    private fun initDi(context: Context?) {
        logger.trace("initDi")
        val app = context as App
        Dagger.set(DaggerAppComponent.builder().appModule(AppModule(app)).build())
        Dagger.get().inject(this)
    }

    private fun initExternalSQLiteTool(context: Context?) {
        SQLiteStudioService(context, BuildConfig.SQL_STUDIO_PORT).start()
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int = 0

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = 0

    override fun getType(uri: Uri): String? = null

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? = null
}