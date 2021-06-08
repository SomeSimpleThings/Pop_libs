package com.somethingsimple.poplibs.ui.users

import com.github.terrakok.cicerone.Router
import com.somethingsimple.poplibs.data.UsersRepository
import com.somethingsimple.poplibs.data.model.GithubUser
import com.somethingsimple.poplibs.exception.SomethingLoadingException
import com.somethingsimple.poplibs.ui.IScreens
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
            users[view.pos].also {
                view.setLogin(it.login)

            }
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
        usersRepo.fetchUsers().subscribe(
            ::onUserLoaded,
            ::onUserLoadError
        )
    }

    private fun onUserLoadError(throwable: Throwable?) {
        if (throwable is SomethingLoadingException)
            viewState.loadingError(throwable.localizedMessage)
    }

    private fun onUserLoaded(users: List<GithubUser>) {
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}