package com.somethingsimple.poplibs.data.user.datasource.local

import com.somethingsimple.poplibs.data.db.GithubCacheDbFactory

object CachedUserDataSourceFactory {


    private val cacheUserDataSource: CachedUserDataSource by lazy {

        CachedUserDataSourceImpl(
            GithubCacheDbFactory
                .create()
        )
    }

    fun create(): CachedUserDataSource = cacheUserDataSource

}
