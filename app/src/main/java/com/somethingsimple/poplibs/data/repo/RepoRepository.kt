package com.somethingsimple.poplibs.data.repo

import com.somethingsimple.poplibs.data.repo.model.GithubRepo
import io.reactivex.rxjava3.core.Single

interface RepoRepository {
    fun getReposForUser(username: String): Single<List<GithubRepo>>
    fun getRepoByName(username: String, reponame: String): Single<GithubRepo>
}