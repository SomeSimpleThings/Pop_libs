package com.somethingsimple.poplibs.ui.users.detail

import com.github.terrakok.cicerone.Router
import com.somethingsimple.poplibs.data.model.GithubUser
import moxy.MvpPresenter

class UserDetailPresenter(private val router: Router) :
    MvpPresenter<UserDetailView>() {

    fun showUser(user: GithubUser) {
        viewState.showUser(user)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}