package com.somethingsimple.poplibs.data.user

import com.somethingsimple.poplibs.data.user.model.GithubUser
import com.somethingsimple.poplibs.exception.UserNotFoundException
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

class GithubUsersRepoMocked(private val users: List<GithubUser>) : UsersRepository {
    override fun fetchUsers(): @NonNull Single<List<GithubUser>> {
        return Single.just(users)
    }

    override fun fetchUserById(userId: Int): @NonNull Maybe<GithubUser> =
        users.firstOrNull { user -> user.id == userId }
            ?.let { Maybe.just(it) }
            ?: Maybe.error(UserNotFoundException(userId.toString()))

}

object MockedUsers {

    val users: List<GithubUser> =
        listOf()
}