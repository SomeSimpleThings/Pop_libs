package com.somethingsimple.poplibs.ui.users.detail

import com.github.terrakok.cicerone.Router
import com.somethingsimple.poplibs.data.UsersRepository
import com.somethingsimple.poplibs.data.model.GithubUser
import moxy.MvpPresenter

class UserDetailPresenter(
    private val usersRepo: UsersRepository,
    private val router: Router
) :
    MvpPresenter<UserDetailView>() {

    fun showUser(user: GithubUser) {
        //эта манипуляция только для практики RX, специально ищу пользователя с неправильным id
        usersRepo.fetchUserById(user.id + 100).subscribe(
            ::onUserFetched,
            ::onFetchFailed
        )
    }

    private fun onFetchFailed(throwable: Throwable?) {
        viewState.showUserNotFound()
    }

    private fun onUserFetched(user: GithubUser) {
        viewState.showUser(user)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}