package com.d9tilov.android.websockettestapp.base.di

import com.d9tilov.android.websockettestapp.App.Companion.TAG
import com.d9tilov.android.websockettestapp.BuildConfig
import com.d9tilov.android.websockettestapp.base.socket.RxWebSocket
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(
        sslSocketFactory: SSLSocketFactory,
        trustManager: TrustManager,
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .sslSocketFactory(sslSocketFactory, arrayOf(trustManager)[0] as X509TrustManager)
            .build()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag(TAG).d("HTTP REQUEST: $message")
            }
        }).apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    fun provideTrustManager(): TrustManager {
        return object : X509TrustManager {
            override fun checkClientTrusted(
                chain: Array<X509Certificate>,
                authType: String
            ) {
            }

            override fun checkServerTrusted(
                chain: Array<X509Certificate>,
                authType: String
            ) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
    }

    @Provides
    fun provideSslSocketFactory(trustManager: TrustManager): SSLSocketFactory {
        val sslContext = SSLContext.getInstance(PROTOCOL_NAME)
        sslContext.init(null, arrayOf(trustManager), SecureRandom())
        return sslContext.socketFactory
    }

    @Provides
    fun provideRequest(): Request {
        return Request.Builder().url(BASE_URL).build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideRxWebSocket(client: OkHttpClient, request: Request) = RxWebSocket(client, request)

    companion object {
        private const val READ_TIMEOUT = 15L
        private const val WRITE_TIMEOUT = 15L
        private const val CONNECT_TIMEOUT = 30L
        private const val PROTOCOL_NAME = "TLS"
        private const val BASE_URL = "wss://quotes.eccalls.mobi:18400"
    }
}
