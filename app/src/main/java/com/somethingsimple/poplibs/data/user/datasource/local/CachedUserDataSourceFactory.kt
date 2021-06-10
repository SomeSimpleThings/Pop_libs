package com.somethingsimple.poplibs.data.user.datasource.local

object CachedUserDataSourceFactory {

    private val cacheUserDataSource: CachedUserDataSource by lazy {
        CachedUserDataSourceImpl()
    }

    fun create(): CachedUserDataSource = cacheUserDataSource
}
