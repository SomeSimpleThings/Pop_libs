package com.somethingsimple.poplibs.data.repo.datasource

import com.somethingsimple.poplibs.data.repo.model.GithubRepo
import io.reactivex.rxjava3.core.Single

interface RepoDataSource {
    fun getReposForUser(userId: Int): Single<List<GithubRepo>>
    fun getRepoById(id: Int): Single<GithubRepo>
}