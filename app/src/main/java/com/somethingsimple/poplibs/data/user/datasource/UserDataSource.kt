package com.somethingsimple.poplibs.data.user.datasource

import com.somethingsimple.poplibs.data.user.model.GithubUser
import io.reactivex.rxjava3.core.Single

interface UserDataSource {
    fun getUsers(since: Int? = null): Single<List<GithubUser>>
    fun getUserByLogin(login: String): Single<GithubUser>
}