package com.somethingsimple.poplibs.ui.repo

import com.github.terrakok.cicerone.Router
import com.somethingsimple.poplibs.data.repo.RepoRepository
import com.somethingsimple.poplibs.data.repo.model.GithubRepo
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class RepoPresenter(
    private val repoRepository: RepoRepository,
    private val router: Router,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : MvpPresenter<RepoView>() {

    fun getRepoInfo(id: Int) {
        compositeDisposable.add(
            repoRepository
                .getRepoById(id)
                .observeOn(scheduler)
                .subscribe(::onRepoLoaded, ::onLoadError)
        )
    }

    private fun onLoadError(throwable: Throwable) {
        throwable?.localizedMessage?.let {
            viewState.showLoadError(it)
        }
    }

    private fun onRepoLoaded(githubRepo: GithubRepo) {
        viewState.showRepoDetails(githubRepo)

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




