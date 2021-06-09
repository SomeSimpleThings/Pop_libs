package com.somethingsimple.poplibs.data.user.datasource.local

import com.somethingsimple.poplibs.data.user.datasource.UserDataSource
import com.somethingsimple.poplibs.data.user.model.GithubUser
import io.reactivex.rxjava3.core.Single

interface CachedUserDataSource : UserDataSource {
    fun retain(users: List<GithubUser>): Single<List<GithubUser>>
}