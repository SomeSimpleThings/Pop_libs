package com.somethingsimple.poplibs.data.repo.datasource.local

class CachedRepoDataSourceFactory {
    private val cacheUserDataSource: CachedRepoDataSource by lazy {
        CachedRepoDataSourceImpl()
    }

    fun create(): CachedRepoDataSource = cacheUserDataSource
}