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
    override fun getReposForUser(username: String): Single<List<GithubRepo>> =
        remoteRepoDataSource
            .getReposForUser(username)
            .flatMap(cacheRepoDataSource::retain)
            .subscribeOn(Schedulers.io())


    override fun getRepoByName(username: String, reponame: String): Single<GithubRepo> =
        remoteRepoDataSource
            .getRepoByName(username, reponame)
            .subscribeOn(Schedulers.io())

}