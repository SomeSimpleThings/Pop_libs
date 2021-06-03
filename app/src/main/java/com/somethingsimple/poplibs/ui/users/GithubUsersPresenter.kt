package com.somethingsimple.poplibs.ui.users

import com.github.terrakok.cicerone.Router
import com.somethingsimple.poplibs.data.UsersRepository
import com.somethingsimple.poplibs.data.model.GithubUser
import moxy.MvpPresenter

class GithubUsersPresenter(
    private val usersRepo: UsersRepository,
    private val router: Router,
    private val appScreens: IScreens

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
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(appScreens.userDetails(user))
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