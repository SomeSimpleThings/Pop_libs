package com.somethingsimple.poplibs.data.user.datasource.remote

import com.somethingsimple.poplibs.data.api.GithubApi
import com.somethingsimple.poplibs.data.user.datasource.UserDataSource
import com.somethingsimple.poplibs.data.user.datasource.local.CachedUserDataSourceFactory

object RemoteUserDataSourceFactory {
    fun create(): UserDataSource =
        RemoteUserDataSource(
            GithubApi.create(),
            CachedUserDataSourceFactory.create()
        )
}
