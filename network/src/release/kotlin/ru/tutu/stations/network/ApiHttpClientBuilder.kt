package ru.tutu.stations.network

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author Grigoriy Pryamov.
 */
class ApiHttpClientBuilder @Inject constructor(private val okHttpClient: OkHttpClient) {

    fun build(): OkHttpClient {
        return okHttpClient.newBuilder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    companion object {
        /**
         * Таймаут на подключение/чтение/запись (в секундах)
         */
        const val TIMEOUT = 35L
    }
}