package com.somethingsimple.poplibs.ui.users.detail

import com.github.terrakok.cicerone.Router
import com.somethingsimple.poplibs.data.user.UsersRepository
import com.somethingsimple.poplibs.data.user.model.GithubUser
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class UserDetailPresenter(
    private val usersRepo: UsersRepository,
    private val router: Router,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) :
    MvpPresenter<UserDetailView>() {

    fun showUser(user: GithubUser) {
        compositeDisposable.add(
            usersRepo.getUserByLogin(user.login)
                .observeOn(scheduler)
                .subscribe(
                    ::onUserFetched,
                    ::onFetchFailed
                )
        )
    }

    private fun onFetchFailed(throwable: Throwable?) {
        viewState.showUserNotFound()
    }

    private fun onUserFetched(user: GithubUser) {
        viewState.showUser(user)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}