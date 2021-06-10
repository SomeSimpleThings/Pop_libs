package com.somethingsimple.poplibs.data.repo.datasource.local

import com.somethingsimple.poplibs.data.repo.datasource.RepoDataSource
import com.somethingsimple.poplibs.data.repo.model.GithubRepo
import io.reactivex.rxjava3.core.Single

interface CachedRepoDataSource : RepoDataSource {
    fun retain(users: List<GithubRepo>): Single<List<GithubRepo>>
}