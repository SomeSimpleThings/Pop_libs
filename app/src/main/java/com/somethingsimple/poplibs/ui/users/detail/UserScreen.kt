package com.somethingsimple.poplibs.ui.users.detail

import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.somethingsimple.poplibs.data.model.GithubUser


class UserScreen(private val user: GithubUser) : FragmentScreen {

    override fun createFragment(factory: FragmentFactory) =
        UserDetailFragment.newInstance(user)
}