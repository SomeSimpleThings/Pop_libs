package com.somethingsimple.poplibs.data.repo.datasource.remote

import com.somethingsimple.poplibs.data.api.GithubApi
import com.somethingsimple.poplibs.data.repo.datasource.RepoDataSource

object RemoteRepoDataSourceFactory {
    object RemoteUserDataSourceFactory {
        fun create(): RepoDataSource = RemoteRepoDataSource(GithubApi.create())
    }

}