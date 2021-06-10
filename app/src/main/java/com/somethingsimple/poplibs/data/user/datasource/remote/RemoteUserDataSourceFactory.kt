package com.somethingsimple.poplibs.data.user.datasource.remote

import com.somethingsimple.poplibs.data.api.GithubApi
import com.somethingsimple.poplibs.data.user.datasource.UserDataSource

object RemoteUserDataSourceFactory {
    fun create(): UserDataSource = RemoteUserDataSource(GithubApi.create())
}
