package ru.tutu.stations.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

/**
 * @author Grigoriy Pryamov.
 */
class ApiRetrofitBuilder @Inject constructor(
    /**
     * Конфигурация API.
     */
    private val apiConfig: ApiConfig,
    /**
     * Билдер http-клиента
     */
    private val apiHttpClientBuilder: ApiHttpClientBuilder
)
{
    fun build(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiConfig.apiUrl)
            .client(apiHttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}