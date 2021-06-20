package com.somethingsimple.poplibs.data.user

import com.somethingsimple.poplibs.data.user.datasource.UserDataSource
import com.somethingsimple.poplibs.data.user.datasource.local.CachedUserDataSource
import com.somethingsimple.poplibs.data.user.model.GithubUser
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class UserRepositoryImpl(
    private val remoteUserDataSource: UserDataSource,
    private val cacheUserDataSource: CachedUserDataSource,
) : UsersRepository {


    override fun getUsers(): Single<List<GithubUser>> =
        cacheUserDataSource
            .getUsers()
            .flatMap(::fetchFromCloudIfRequired)
            .subscribeOn(Schedulers.io())

    private fun fetchFromCloudIfRequired(users: List<GithubUser>): Single<List<GithubUser>> =
        if (users.isEmpty()) {
            remoteUserDataSource
                .getUsers()
                .flatMap(cacheUserDataSource::retain)
        } else {
            Single.just(users)
        }


    override fun getUserByLogin(login: String): Observable<GithubUser> =
        Observable.concat(
            cacheUserDataSource
                .getUserByLogin(login)
                .toObservable(),
            remoteUserDataSource
                .getUserByLogin(login)
                .toObservable()
        )
            .subscribeOn(Schedulers.io())

    override fun getUserById(id: Int): Observable<GithubUser> =
        Observable.concat(
            cacheUserDataSource
                .getUserById(id)
                .toObservable(),
            remoteUserDataSource
                .getUserById(id)
                .toObservable()
        )
            .subscribeOn(Schedulers.io())


}
