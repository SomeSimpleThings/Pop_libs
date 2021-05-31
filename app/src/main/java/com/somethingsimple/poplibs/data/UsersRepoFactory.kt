package com.somethingsimple.poplibs.data

object UsersRepoFactory {
    fun create(): UsersRepository = GithubUsersRepoMocked(MockedUsers.users)
}
