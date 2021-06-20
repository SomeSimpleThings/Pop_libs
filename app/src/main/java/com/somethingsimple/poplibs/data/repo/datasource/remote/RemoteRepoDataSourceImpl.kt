package com.somethingsimple.poplibs.data.repo.datasource.remote

import com.somethingsimple.poplibs.data.api.GithubApi
import com.somethingsimple.poplibs.data.repo.datasource.RepoDataSource
import com.somethingsimple.poplibs.data.repo.model.GithubRepo
import com.somethingsimple.poplibs.data.user.datasource.local.CachedUserDataSource
import io.reactivex.rxjava3.core.Single

class RemoteRepoDataSourceImpl(
    private val api: GithubApi,
    private val cachedUserDataSource: CachedUserDataSource
) :
    RepoDataSource {
    override fun getReposForUser(userId: Int): Single<List<GithubRepo>> =
        cachedUserDataSource.getUserById(userId)
            .flatMap { user ->
                api.getReposForUser(user.login)
                    .flatMap { repoList ->
                        repoList.forEach { it.userId = userId }
                        return@flatMap Single.just(repoList)
                    }
            }

    override fun getRepoById(id: Int): Single<GithubRepo> = api.getRepoById(id)

}