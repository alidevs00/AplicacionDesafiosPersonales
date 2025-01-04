package com.example.aplicaciondesafiospersonales.di

import com.example.aplicaciondesafiospersonales.challenges.data.remote.ChallengesApi
import com.example.aplicaciondesafiospersonales.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("X-Parse-Application-Id", "sIt5Rwbgqx1drq2lnudsM4iOgiWwtxMDhS28omFq")
                    .addHeader("X-Parse-REST-API-Key", "KmMnTE3dPbnDpzWMRx3kt0hxAulRl6VfPkFIB41m")
                    .addHeader("Content-Type", "application/json")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Singleton
    fun provideChallengesApi(retrofit: Retrofit): ChallengesApi {
        return retrofit.create(ChallengesApi::class.java)
    }


}