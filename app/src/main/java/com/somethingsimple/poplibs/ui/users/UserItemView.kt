package com.somethingsimple.poplibs.ui.users

import com.somethingsimple.poplibs.ui.common.ItemView

interface UserItemView : ItemView {
    fun setLogin(text: String)
}