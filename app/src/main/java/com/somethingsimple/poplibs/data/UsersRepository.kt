package com.somethingsimple.poplibs.data

import com.somethingsimple.poplibs.data.model.GithubUser
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface UsersRepository {
    fun fetchUsers(): @NonNull Single<List<GithubUser>>
    fun fetchUserById(userId: String): @NonNull Maybe<GithubUser>
}