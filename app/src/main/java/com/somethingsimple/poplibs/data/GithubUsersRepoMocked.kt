package com.somethingsimple.poplibs.data

import com.somethingsimple.poplibs.data.model.GithubUser
import com.somethingsimple.poplibs.exceprion.UserNotFoundException
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

class GithubUsersRepoMocked(private val users: List<GithubUser>) : UsersRepository {
    override fun fetchUsers(): @NonNull Single<List<GithubUser>> {
        return Single.just(users)
    }

    override fun fetchUserById(userId: String): @NonNull Maybe<GithubUser> =
        users.firstOrNull { user -> user.id == userId }
            ?.let { Maybe.just(it) }
            ?: Maybe.error(UserNotFoundException(userId))

}

object MockedUsers {

    val users: List<GithubUser> =
        listOf(
            GithubUser(id = "01", login = "User01"),
            GithubUser(id = "02", login = "User02"),
            GithubUser(id = "03", login = "User03"),
            GithubUser(id = "04", login = "User04"),
            GithubUser(id = "05", login = "User05"),
            GithubUser(id = "06", login = "User06"),
            GithubUser(id = "07", login = "User07"),
            GithubUser(id = "08", login = "User08"),
            GithubUser(id = "09", login = "User09"),
            GithubUser(id = "10", login = "User10"),
            GithubUser(id = "11", login = "User11"),
            GithubUser(id = "12", login = "User12"),
            GithubUser(id = "13", login = "User13"),
            GithubUser(id = "14", login = "User14"),
            GithubUser(id = "15", login = "User15"),
            GithubUser(id = "16", login = "User16"),
            GithubUser(id = "17", login = "User17"),
            GithubUser(id = "18", login = "User18"),
            GithubUser(id = "19", login = "User19"),
            GithubUser(id = "20", login = "User20"),
        )
}