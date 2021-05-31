package com.somethingsimple.poplibs.ui

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.somethingsimple.poplibs.ui.users.UsersFragment
import com.somethingsimple.poplibs.ui.users.detail.UserDetailFragment

interface IScreens {
    fun users(): Screen
    fun userDetails(): Screen
}

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun userDetails() = FragmentScreen { UserDetailFragment.newInstance() }
}