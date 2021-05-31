package com.somethingsimple.poplibs.ui.users

import com.github.terrakok.cicerone.Router
import com.somethingsimple.poplibs.data.UsersRepository
import com.somethingsimple.poplibs.data.model.GithubUser
import com.somethingsimple.poplibs.ui.IScreens
import moxy.MvpPresenter

class GithubUsersPresenter(
    val usersRepo: UsersRepository,
    val router: Router
) :
    MvpPresenter<UsersView>() {
    class UsersListPresenter : IUsersListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
//            router.navigateTo(UserScreen(user.id))
        }
    }

    private fun loadData() {
        val users = usersRepo.fetchUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}