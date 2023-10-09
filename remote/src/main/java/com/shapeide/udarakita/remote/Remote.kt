package com.shapeide.udarakita.remote

import android.content.Context
import com.google.gson.GsonBuilder
import com.shapeide.udarakita.remote.domain.base.ApiEndpoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.Properties

@Module
@InstallIn(SingletonComponent::class)
object Remote {
    @Provides
    fun provideApiConfigValue(
        @ApplicationContext context: Context
    ): String{
        val properties = Properties()
        return try {
            val rawResource = context.resources.openRawResource(R.raw.config)
            properties.load(rawResource)
            return properties.getProperty("api_app_key")
        }catch (e: Exception){
            Timber.e(e)
            "-"
        }
    }

    @Provides
    fun provideApiEndpoint(
        apiConfigValue: String
    ): ApiEndpoint {
        val interceptor = Interceptor { chain: Interceptor.Chain ->
            var request = chain.request()
            val authParam = request.url.newBuilder()
                .addQueryParameter("app_key", apiConfigValue)
                .build()
            request = request.newBuilder().url(authParam).build()
            return@Interceptor chain.proceed(request)
        }

        val client = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            )
            .build()

        return Retrofit.Builder()
            .baseUrl(ApiEndpoint.BASE_URL)
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().setLenient().create())
            )
            .build()
            .create(ApiEndpoint::class.java)
    }
}