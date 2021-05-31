package com.somethingsimple.poplibs.ui.users.detail

import com.github.terrakok.cicerone.Router
import com.somethingsimple.poplibs.data.model.GithubUser
import moxy.MvpPresenter

class UserDetailPresenter(private val router: Router, user: GithubUser?) :
    MvpPresenter<UserDetailView>() {

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}