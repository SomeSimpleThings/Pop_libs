package com.somethingsimple.poplibs.data.user.datasource.remote

import com.somethingsimple.poplibs.data.user.datasource.UserDataSource
import com.somethingsimple.poplibs.data.user.model.GithubUser
import io.reactivex.rxjava3.core.Single

class RemoteUserDataSource() : UserDataSource {
    override fun getUsers(since: Int?): Single<List<GithubUser>> {
        TODO("Not yet implemented")
    }

    override fun getUserByLogin(login: String): Single<GithubUser> {
        TODO("Not yet implemented")
    }
}