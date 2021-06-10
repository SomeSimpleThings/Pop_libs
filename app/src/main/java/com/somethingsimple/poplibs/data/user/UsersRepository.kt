package com.somethingsimple.poplibs.data.user

import com.somethingsimple.poplibs.data.user.model.GithubUser
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface UsersRepository {
    fun getUsers(): @NonNull Single<List<GithubUser>>
    fun getUserByLogin(login: String): @NonNull Observable<GithubUser>
}