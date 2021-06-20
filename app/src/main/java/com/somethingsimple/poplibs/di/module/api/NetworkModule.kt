package com.somethingsimple.poplibs.di.module.api

import android.content.Context
import com.somethingsimple.poplibs.R
import com.somethingsimple.poplibs.data.api.GithubApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGitHubApi(context: Context): GithubApi =
        Retrofit.Builder()
            .baseUrl(context.getString(R.string.github_api_url))
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApi::class.java)

}
