package com.somethingsimple.poplibs.data.repo.datasource.remote

import com.somethingsimple.poplibs.data.api.GithubApi
import com.somethingsimple.poplibs.data.repo.datasource.RepoDataSource
import com.somethingsimple.poplibs.data.user.datasource.local.CachedUserDataSourceFactory

object RemoteRepoDataSourceFactory {
    fun create(): RepoDataSource =
        RemoteRepoDataSourceImpl(
            GithubApi.create(),
            CachedUserDataSourceFactory.create()
        )
}