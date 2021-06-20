package com.somethingsimple.poplibs.data.db

import androidx.room.Room
import com.somethingsimple.poplibs.PopLibsApplication.ContextHolder.context

object GithubCacheDbFactory {
    private val databaseGitHubStorage: GithubCacheDb by lazy {
        Room.databaseBuilder(context, GithubCacheDb::class.java, "github.db")

            .build()
    }

    fun create(): GithubCacheDb = databaseGitHubStorage
}