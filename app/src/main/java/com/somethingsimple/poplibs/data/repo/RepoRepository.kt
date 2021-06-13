package com.somethingsimple.poplibs.data.repo

import com.somethingsimple.poplibs.data.repo.model.GithubRepo
import io.reactivex.rxjava3.core.Single

interface RepoRepository {
    fun getReposForUser(userId: Int): Single<List<GithubRepo>>
    fun getRepoById(id: Int): Single<GithubRepo>
}