package com.somethingsimple.poplibs.data.user.datasource.local

import com.somethingsimple.poplibs.data.db.GithubCacheDb
import com.somethingsimple.poplibs.data.db.user.UserDao
import com.somethingsimple.poplibs.data.user.model.GithubUser
import io.reactivex.rxjava3.core.Single

class CachedUserDataSourceImpl(cacheDb: GithubCacheDb) : CachedUserDataSource {

    private val gitHubUserDao: UserDao =
        cacheDb
            .gitHubUserDao()

    override fun retain(users: List<GithubUser>): Single<List<GithubUser>> =
        gitHubUserDao
            .retain(users)
            .andThen(getUsers())

    override fun retain(user: GithubUser): Single<GithubUser> =
        gitHubUserDao
            .retain(user)
            .andThen(getUserById(user.id))

    override fun getUsers(since: Int?): Single<List<GithubUser>> =
        gitHubUserDao.fetchUsers()

    override fun getUserByLogin(login: String): Single<GithubUser> =
        gitHubUserDao
            .fetchUserByLogin(login)
//            .onErrorResumeNext(Single.error(RuntimeException("Нет такого пользователя")))

    override fun getUserById(id: Int): Single<GithubUser> =
        gitHubUserDao
            .fetchUserById(id)

}