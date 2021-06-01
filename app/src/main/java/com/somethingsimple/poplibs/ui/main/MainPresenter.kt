package com.somethingsimple.poplibs.ui.main

import com.github.terrakok.cicerone.Router
import com.somethingsimple.poplibs.ui.users.IScreens
import moxy.MvpPresenter

class MainPresenter(private val router: Router,private val screens: IScreens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}
