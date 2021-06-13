package com.somethingsimple.poplibs.data.user.datasource.remote

import com.somethingsimple.poplibs.data.api.GithubApi
import com.somethingsimple.poplibs.data.db.user.UserDao
import com.somethingsimple.poplibs.data.user.datasource.UserDataSource
import com.somethingsimple.poplibs.data.user.model.GithubUser
import io.reactivex.rxjava3.core.Single

class RemoteUserDataSource(private val api: GithubApi, private val userDao: UserDao) :
    UserDataSource {
    override fun getUsers(since: Int?): Single<List<GithubUser>> =
        api.getUsers(since)
            .flattenAsObservable { users -> users }
            .concatMapSingle { user -> api.getUserByUsername(user.login) }
            .toList()


    override fun getUserByLogin(login: String): Single<GithubUser> =
        api.getUserByUsername(login)

    override fun getUserById(id: Int): Single<GithubUser> =
        userDao.fetchUserById(id).flatMap {
            api.getUserByUsername(it.login)
        }
}