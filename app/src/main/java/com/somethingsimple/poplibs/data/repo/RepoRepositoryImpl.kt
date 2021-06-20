package com.somethingsimple.poplibs.data.repo

import com.somethingsimple.poplibs.data.repo.datasource.RepoDataSource
import com.somethingsimple.poplibs.data.repo.datasource.local.CachedRepoDataSource
import com.somethingsimple.poplibs.data.repo.model.GithubRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RepoRepositoryImpl(
    private val remoteRepoDataSource: RepoDataSource,
    private val cacheRepoDataSource: CachedRepoDataSource,
) : RepoRepository {
    override fun getReposForUser(userId: Int): Single<List<GithubRepo>> =
        remoteRepoDataSource
            .getReposForUser(userId)
            .flatMap(cacheRepoDataSource::retain)
            .subscribeOn(Schedulers.io())


    override fun getRepoById(id: Int): Single<GithubRepo> =
        remoteRepoDataSource
            .getRepoById(id)
            .subscribeOn(Schedulers.io())

}