package com.somethingsimple.poplibs.ui.users.detail

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserDetailPresenter(private val router: Router) : MvpPresenter<UserDetailView>() {

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}