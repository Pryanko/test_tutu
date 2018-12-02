package ru.tutu.stations.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author Grigoriy Pryamov.
 */
@Module
class ApiModule {

    @Provides
    @Singleton
    fun httpClient() = OkHttpClient()

    @Provides
    fun apiConfig() = ApiConfig(BuildConfig.API_BASE_URL)

    @Provides
    @Singleton
    fun retrofitProvider(apiRetrofitBuilder: ApiRetrofitBuilder) = apiRetrofitBuilder.build()

    @Provides
    @Singleton
    fun responseConverter() = ResponseConverter()

    @Provides
    @Singleton
    fun apiWorker(retrofit: Retrofit, responseConverter: ResponseConverter): ApiWorker =
        DefaultApiWorker(retrofit.create(Api::class.java), responseConverter)
}