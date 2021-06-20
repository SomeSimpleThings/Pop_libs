package com.somethingsimple.poplibs.ui.users

import com.github.terrakok.cicerone.Router
import com.somethingsimple.poplibs.data.user.UsersRepository
import com.somethingsimple.poplibs.data.user.model.GithubUser
import com.somethingsimple.poplibs.exception.SomethingLoadingException
import com.somethingsimple.poplibs.schedulers.Schedulers
import com.somethingsimple.poplibs.ui.IScreens
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class GithubUsersPresenter(
    private val usersRepo: UsersRepository,
    private val router: Router,
    private val appScreens: IScreens,
    private val scheduler: Schedulers,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


) :
    MvpPresenter<UsersView>() {
    class UsersListPresenter : IUsersListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            view.bind(users[view.pos])
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(appScreens.userDetails(user.id))
        }
    }

    private fun loadData() {
        compositeDisposable.add(
            usersRepo.getUsers()
                .observeOn(scheduler.main())
                .subscribe(
                    ::onUsersLoaded,
                    ::onUserLoadError
                )
        )
    }

    private fun onUserLoadError(throwable: Throwable?) {
        if (throwable is SomethingLoadingException)
            viewState.loadingError(throwable.localizedMessage)
    }

    private fun onUsersLoaded(users: List<GithubUser>) {
        usersListPresenter.users.clear()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}