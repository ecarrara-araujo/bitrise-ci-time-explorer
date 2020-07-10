package br.com.ecarrara.core.networking

import br.com.ecarrara.core.config.BITRISE_API_KEY
import br.com.ecarrara.core.config.BITRISE_BASE_URL
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val restClient: Retrofit by lazy { createRetrofitClient() }

inline fun <reified T> createRestService(service: Class<T>): T = restClient.create(service)

private fun createRetrofitClient(
    authToken: String = BITRISE_API_KEY,
    baseUrl: String = BITRISE_BASE_URL
): Retrofit {
    val httpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val builder = original.newBuilder()
                .header("Accept", "application/json")
                .header("Authorization", authToken)
            val request = builder.build()
            chain.proceed(request)
        }
        .addInterceptor(setUpLogger())
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(setUpMoshi()))
        .client(httpClient)
        .build()
}

private fun setUpLogger() = HttpLoggingInterceptor().apply {
    setLevel(HttpLoggingInterceptor.Level.BODY)
}

private fun setUpMoshi(): Moshi {
    return Moshi.Builder().build()
}