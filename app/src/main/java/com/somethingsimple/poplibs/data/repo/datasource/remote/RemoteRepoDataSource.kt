package com.somethingsimple.poplibs.data.repo.datasource.remote

import com.somethingsimple.poplibs.data.api.GithubApi
import com.somethingsimple.poplibs.data.repo.datasource.RepoDataSource
import com.somethingsimple.poplibs.data.repo.model.GithubRepo
import io.reactivex.rxjava3.core.Single

class RemoteRepoDataSource(private val api: GithubApi) : RepoDataSource {
    override fun getReposForUser(username: String): Single<List<GithubRepo>> =
        api.getReposForUser(username)


    override fun getRepoByName(username: String, reponame: String): Single<GithubRepo> =
        api.getRepoByName(username, reponame)

    override fun getRepoById(id: Int): Single<GithubRepo> = api.getRepoById(id)

}