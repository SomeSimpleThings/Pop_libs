package com.somethingsimple.poplibs.ui.user

import com.github.terrakok.cicerone.Router
import com.somethingsimple.poplibs.data.repo.RepoRepository
import com.somethingsimple.poplibs.data.repo.model.GithubRepo
import com.somethingsimple.poplibs.data.user.UsersRepository
import com.somethingsimple.poplibs.data.user.model.GithubUser
import com.somethingsimple.poplibs.schedulers.Schedulers
import com.somethingsimple.poplibs.ui.IScreens
import com.somethingsimple.poplibs.ui.repos.RepoItemView
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class UserDetailPresenter(
    private val usersRepo: UsersRepository,
    private val repoRepo: RepoRepository,
    private val router: Router,
    private val schedulers: Schedulers,
    private val appScreens: IScreens,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) :
    MvpPresenter<UserDetailView>() {

    class RepoListPresenterImpl : com.somethingsimple.poplibs.ui.repos.RepoListPresenter {

        val repos = mutableListOf<GithubRepo>()

        override var itemClickListener: ((RepoItemView) -> Unit)? = null

        override fun bindView(view: RepoItemView) {
            view.bind(repos[view.pos])
        }

        override fun getCount() = repos.size
    }

    val reposListPresenter = RepoListPresenterImpl()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initRepoList()
        reposListPresenter.itemClickListener = { itemView ->
            val repoRepo = reposListPresenter.repos[itemView.pos]
            router.navigateTo(appScreens.repoDetails(repoRepo.id))
        }
    }

    fun showUser(userId: Int) {
        compositeDisposable.add(
            usersRepo.getUserById(userId)
                .observeOn(schedulers.main())
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
        compositeDisposable.add(
            repoRepo
                .getReposForUser(user.id)
                .observeOn(schedulers.main())
                .subscribe(
                    ::onReposFetched,
                    ::onReposFetchFailed
                )
        )
    }

    private fun onReposFetched(list: List<GithubRepo>) {
        reposListPresenter.repos.clear()
        reposListPresenter.repos.addAll(list)
        viewState.updateRepoList()
    }

    private fun onReposFetchFailed(throwable: Throwable?) {
        viewState.showUserNotFound()
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}