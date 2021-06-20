package com.somethingsimple.poplibs.ui.users

import com.somethingsimple.poplibs.data.user.model.GithubUser
import com.somethingsimple.poplibs.ui.common.ItemView

interface UserItemView : ItemView {
    fun bind(githubUser: GithubUser)
}