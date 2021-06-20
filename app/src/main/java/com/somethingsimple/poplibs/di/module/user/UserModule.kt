package com.somethingsimple.poplibs.di.module.user

import com.somethingsimple.poplibs.data.api.GithubApi
import com.somethingsimple.poplibs.data.db.GithubCacheDb
import com.somethingsimple.poplibs.data.user.UserRepositoryImpl
import com.somethingsimple.poplibs.data.user.UsersRepository
import com.somethingsimple.poplibs.data.user.datasource.UserDataSource
import com.somethingsimple.poplibs.data.user.datasource.local.CachedUserDataSource
import com.somethingsimple.poplibs.data.user.datasource.local.CachedUserDataSourceImpl
import com.somethingsimple.poplibs.data.user.datasource.remote.RemoteUserDataSource
import com.somethingsimple.poplibs.di.Cache
import com.somethingsimple.poplibs.di.Cloud
import com.somethingsimple.poplibs.di.Persisted
import com.somethingsimple.poplibs.di.module.api.NetworkModule
import com.somethingsimple.poplibs.di.module.db.DbModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [UserUiModule::class, NetworkModule::class, DbModule::class])
class UserModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        cloudUserDataSource: UserDataSource,
        cacheUserDataSource: CachedUserDataSource
    ): UsersRepository =
        UserRepositoryImpl(
            cloudUserDataSource,
            cacheUserDataSource
        )

    @Singleton
    @Provides
    fun provideCloudUserDataSource(
        gitHubApi: GithubApi,
        cacheUserDataSource: CachedUserDataSource
    ): UserDataSource =
        RemoteUserDataSource(gitHubApi, cacheUserDataSource)

    @Singleton
    @Provides
    fun provideCachedUserDataSource(
        @Persisted gitHubDb: GithubCacheDb
    ): CachedUserDataSource =
        CachedUserDataSourceImpl(gitHubDb)
}