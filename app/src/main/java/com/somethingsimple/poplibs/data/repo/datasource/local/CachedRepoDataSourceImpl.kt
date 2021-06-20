package com.somethingsimple.poplibs.data.repo.datasource.local

import com.somethingsimple.poplibs.data.db.GithubCacheDb
import com.somethingsimple.poplibs.data.db.repo.RepoDao
import com.somethingsimple.poplibs.data.repo.model.GithubRepo
import io.reactivex.rxjava3.core.Single

class CachedRepoDataSourceImpl(githubCacheDb: GithubCacheDb) : CachedRepoDataSource {

    private val gitHubRepoDao: RepoDao =
        githubCacheDb
            .gitHubRepoDao()

    override fun retain(repos: List<GithubRepo>): Single<List<GithubRepo>> =
        gitHubRepoDao
            .retain(repos)
            .andThen(getReposForUser(repos[0].userId))


    override fun getReposForUser(userId: Int): Single<List<GithubRepo>> =
        gitHubRepoDao.fetchReposForUser(userId)


    override fun getRepoById(id: Int): Single<GithubRepo> =
        gitHubRepoDao.fetchRepoById(id)


}