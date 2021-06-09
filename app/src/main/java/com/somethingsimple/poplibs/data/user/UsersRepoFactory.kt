package com.somethingsimple.poplibs.data.user

object UsersRepoFactory {
    fun create(): UsersRepository = GithubUsersRepoMocked(MockedUsers.users)
}
