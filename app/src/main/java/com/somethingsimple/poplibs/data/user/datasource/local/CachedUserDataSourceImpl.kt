package com.somethingsimple.poplibs.data.user.datasource.local

import com.somethingsimple.poplibs.data.user.model.GithubUser
import com.somethingsimple.poplibs.exception.UserNotFoundException
import io.reactivex.rxjava3.core.Single

class CachedUserDataSourceImpl : CachedUserDataSource {

    private val cache = mutableListOf<GithubUser>()

    override fun retain(users: List<GithubUser>): Single<List<GithubUser>> =
        Single.fromCallable {
            cache.clear()
            cache.addAll(users)
            cache
        }

    override fun getUsers(since: Int?): Single<List<GithubUser>> =
        Single.just(cache)

    override fun getUserByLogin(login: String): Single<GithubUser> =
        Single.defer {
            cache.firstOrNull { it.login == login }?.let { Single.just(it) }
                ?: Single.error(UserNotFoundException(login))
        }

    override fun getUserById(id: Int): Single<GithubUser> =
        Single.defer {
            cache.firstOrNull { it.id == id }?.let { Single.just(it) }
                ?: Single.error(UserNotFoundException("$id"))
        }

}