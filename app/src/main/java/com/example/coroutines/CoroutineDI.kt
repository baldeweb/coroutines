package com.example.coroutines

import com.cornershop.counterthings.data.service.CoroutineCallAdapterFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

const val TIMEOUT_30_SECONDS = 10L
const val BASE_URL = "http://xxx/"
const val SSL = "SSL"

val viewModelModules = module {
//    viewModel { BaseViewModel() }
}

val serviceModules = module {
    single {
        createWebService<SWAPI>(
                okHttpClient = createHttpClient(),
                baseUrl = BASE_URL
        )
    }
}

fun createHttpClient(): OkHttpClient = getUnsafeOkHttpClient()
        .newBuilder()
        .connectTimeout(TIMEOUT_30_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_30_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_30_SECONDS, TimeUnit.SECONDS)
        .addInterceptor(configDebugLog())
        .build()

fun getUnsafeOkHttpClient(): OkHttpClient {
    return try {
        val trustAllCerts =
                arrayOf<TrustManager>(
                        object : X509TrustManager {
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
                )
        val sslContext = SSLContext.getInstance(SSL)
        sslContext.init(null, trustAllCerts, SecureRandom())
        val sslSocketFactory = sslContext.socketFactory
        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(
                sslSocketFactory,
                trustAllCerts[0] as X509TrustManager
        )
        builder.hostnameVerifier { hostname: String?, session: SSLSession? -> true }
        builder.build()
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}

inline fun <reified T> createWebService(
        okHttpClient: OkHttpClient, baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    return retrofit.create(T::class.java)
}

private fun configDebugLog(): HttpLoggingInterceptor {
    val log = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG)
        log.level = HttpLoggingInterceptor.Level.BODY
    return log
}