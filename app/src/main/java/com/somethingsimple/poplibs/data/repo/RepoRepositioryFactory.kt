package com.somethingsimple.poplibs.data.repo

import com.somethingsimple.poplibs.data.repo.datasource.local.CachedRepoDataSourceFactory
import com.somethingsimple.poplibs.data.repo.datasource.remote.RemoteRepoDataSourceFactory

object RepoRepositioryFactory {
    fun create(): RepoRepository = RepoRepositoryImpl(
        RemoteRepoDataSourceFactory.create(),
        CachedRepoDataSourceFactory.create()
    )
}