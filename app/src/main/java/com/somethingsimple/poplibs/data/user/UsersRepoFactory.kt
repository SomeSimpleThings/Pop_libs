package com.somethingsimple.poplibs.data.user

import com.somethingsimple.poplibs.data.user.datasource.local.CachedUserDataSourceFactory
import com.somethingsimple.poplibs.data.user.datasource.remote.RemoteUserDataSourceFactory

object UsersRepoFactory {
    fun create(): UsersRepository = UserRepositoryImpl(
        RemoteUserDataSourceFactory.create(),
        CachedUserDataSourceFactory.create()
    )
}
