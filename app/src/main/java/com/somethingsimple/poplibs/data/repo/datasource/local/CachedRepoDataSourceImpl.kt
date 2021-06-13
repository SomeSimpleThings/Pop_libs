package com.somethingsimple.poplibs.data.repo.datasource.local

import com.somethingsimple.poplibs.data.repo.model.GithubRepo
import com.somethingsimple.poplibs.exception.RepoNotFoundException
import com.somethingsimple.poplibs.exception.UserNotFoundException
import io.reactivex.rxjava3.core.Single

class CachedRepoDataSourceImpl : CachedRepoDataSource {

    private val cache = mutableListOf<GithubRepo>()

    override fun retain(repos: List<GithubRepo>): Single<List<GithubRepo>> =
        Single.fromCallable {
            cache.clear()
            cache.addAll(repos)
            cache
        }


    override fun getReposForUser(id: Int): Single<List<GithubRepo>> =
        Single
            .defer {
                cache.filter { it.userId == id }
                    ?.let { Single.just(it) }
            }
            ?: Single.error(UserNotFoundException("$id"))


    override fun getRepoById(id: Int): Single<GithubRepo> =
        Single.defer {
            cache.firstOrNull { it.id == id }
                ?.let { Single.just(it) }
                ?: Single.error(RepoNotFoundException(id))
        }
}