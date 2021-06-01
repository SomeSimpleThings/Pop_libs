package com.somethingsimple.poplibs.ui.common

interface IListPresenter<V : ItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

