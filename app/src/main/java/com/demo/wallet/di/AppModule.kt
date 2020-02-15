package com.demo.wallet.di

import android.content.Context
import androidx.room.Room
import com.demo.wallet.AppConstants.API_SERVICE_AUTH_HEADER
import com.demo.wallet.AppConstants.BASE_URL
import com.demo.wallet.BuildConfig
import com.demo.wallet.WalletApplication
import com.demo.wallet.api.BigDecimalAdapter
import com.demo.wallet.api.BigIntegerAdapter
import com.demo.wallet.api.WalletAPI
import com.demo.wallet.db.AppDatabase
import com.demo.wallet.db.WalletDao
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module(includes = [ViewModelsModule::class])
class AppModule() {

    @Provides
    @Singleton
    fun provideContext(app: WalletApplication): Context = app

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {

        //Creating Auth Interceptor to add api_key query in front of all the requests.
        val authInterceptor = Interceptor { chain ->
            val newUrl = chain.request().url()
                .newBuilder()
                .addQueryParameter(API_SERVICE_AUTH_HEADER, BuildConfig.xPubAddress)
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }

        // Log requests with apiKey only on DEBUG mode
        val httpLoggingInterceptor = if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY
        ) else null

        return OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(httpClient: OkHttpClient): WalletAPI {

        // custom Moshi converter with support for BigInteger
        val moshi =  Moshi.Builder()
            .add(BigDecimalAdapter)
            .add(BigIntegerAdapter)
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(httpClient)
            .build()
            .create(WalletAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideDb(app: WalletApplication): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, "crypto.db")
            // At current moment we don't want to provide migrations and specifically want database to be cleared when upgrade the version.
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideWalletDao(db: AppDatabase): WalletDao {
        return db.walletDao()
    }
}
