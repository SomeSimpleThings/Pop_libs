package com.somethingsimple.poplibs.data

import com.somethingsimple.poplibs.data.model.GithubUser

interface UsersRepository {
    fun fetchUsers(): List<GithubUser>
    fun fetchUserById(userId: String): GithubUser?
}