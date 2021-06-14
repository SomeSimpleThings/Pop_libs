package com.somethingsimple.poplibs.data.repo.datasource.local

import com.somethingsimple.poplibs.data.db.GithubCacheDbFactory

object CachedRepoDataSourceFactory {
    private val cacheRepoDataSource: CachedRepoDataSource by lazy {

        CachedRepoDataSourceImpl(
            GithubCacheDbFactory
                .create()
        )
    }

    fun create(): CachedRepoDataSource = cacheRepoDataSource
}