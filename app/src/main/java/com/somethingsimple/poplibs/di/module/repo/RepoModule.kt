package com.somethingsimple.poplibs.di.module.repo

import com.somethingsimple.poplibs.data.api.GithubApi
import com.somethingsimple.poplibs.data.db.GithubCacheDb
import com.somethingsimple.poplibs.data.repo.RepoRepository
import com.somethingsimple.poplibs.data.repo.RepoRepositoryImpl
import com.somethingsimple.poplibs.data.repo.datasource.RepoDataSource
import com.somethingsimple.poplibs.data.repo.datasource.local.CachedRepoDataSource
import com.somethingsimple.poplibs.data.repo.datasource.local.CachedRepoDataSourceImpl
import com.somethingsimple.poplibs.data.repo.datasource.remote.RemoteRepoDataSourceImpl
import com.somethingsimple.poplibs.data.user.datasource.local.CachedUserDataSource
import com.somethingsimple.poplibs.di.Persisted
import com.somethingsimple.poplibs.di.module.api.NetworkModule
import com.somethingsimple.poplibs.di.module.db.DbModule
import com.somethingsimple.poplibs.di.module.user.UserModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        RepoUiModule::class,
        NetworkModule::class,
        DbModule::class,
        UserModule::class]
)
class RepoModule {
    @Singleton
    @Provides
    fun provideRepoRepository(
        cloudRepoDataSource: RepoDataSource,
        cachedRepoDataSource: CachedRepoDataSource
    ): RepoRepository =
        RepoRepositoryImpl(
            cloudRepoDataSource,
            cachedRepoDataSource
        )

    @Singleton
    @Provides
    fun provideRemoteRepoDataSource(
        gitHubApi: GithubApi,
        cacheUserDataSource: CachedUserDataSource
    ): RepoDataSource =
        RemoteRepoDataSourceImpl(gitHubApi, cacheUserDataSource)

    @Singleton
    @Provides
    fun provideCacheRepoDataSource(
        @Persisted gitHubDb: GithubCacheDb
    ): CachedRepoDataSource =
        CachedRepoDataSourceImpl(gitHubDb)
}