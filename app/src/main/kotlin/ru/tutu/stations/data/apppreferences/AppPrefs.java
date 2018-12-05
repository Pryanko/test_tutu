package ru.tutu.stations.data.apppreferences;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Конфигурация приложения
 *
 * @author Grigoriy Pryamov
 */
@Singleton
public class AppPrefs {

    private static class Entities {
        /**
         * Время синхронизации
         */
        private static final String DATA_SYNC_TIME = "DATA_SYNC_TIME";
    }

    /**
     * Наименование файла
     */
    private static final String SHARED_PREFS_FILE_NAME = "AppPrefs";

    private final SharedPreferences prefs;

    @Inject
    AppPrefs(Context context) {
        prefs = context.getSharedPreferences(SHARED_PREFS_FILE_NAME, Context.MODE_PRIVATE);
    }

    public long getDataSyncTime() {
        return prefs.getLong(Entities.DATA_SYNC_TIME, 0);
    }

    public void setDataSyncTime(long syncTime) {
        prefs.edit().putLong(Entities.DATA_SYNC_TIME, syncTime).apply();
    }
}
