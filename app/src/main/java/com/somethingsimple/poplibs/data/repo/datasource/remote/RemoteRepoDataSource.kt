package com.somethingsimple.poplibs.data.repo.datasource.remote

import com.somethingsimple.poplibs.data.api.GithubApi
import com.somethingsimple.poplibs.data.db.user.UserDao
import com.somethingsimple.poplibs.data.repo.datasource.RepoDataSource
import com.somethingsimple.poplibs.data.repo.model.GithubRepo
import io.reactivex.rxjava3.core.Single

class RemoteRepoDataSource(private val api: GithubApi, private val userDao: UserDao) :
    RepoDataSource {
    override fun getReposForUser(userId: Int): Single<List<GithubRepo>> =
        userDao.fetchUserById(userId)
            .flatMap {
                api.getReposForUser(it.login)
            }

    override fun getRepoById(id: Int): Single<GithubRepo> = api.getRepoById(id)

}