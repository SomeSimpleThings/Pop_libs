package com.somethingsimple.poplibs.di.module.db

import android.content.Context
import androidx.room.Room
import com.somethingsimple.poplibs.data.db.GithubCacheDb
import com.somethingsimple.poplibs.di.Persisted
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {
    @Persisted
    @Singleton
    @Provides
    fun provideDatabaseGitHubStorage(context: Context): GithubCacheDb =
        Room
            .databaseBuilder(context, GithubCacheDb::class.java, "github.db")
            .build()
}