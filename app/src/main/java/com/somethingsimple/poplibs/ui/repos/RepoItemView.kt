package com.somethingsimple.poplibs.ui.repos

import com.somethingsimple.poplibs.data.repo.model.GithubRepo
import com.somethingsimple.poplibs.ui.common.ItemView

interface RepoItemView : ItemView {
    fun bind(repo: GithubRepo)
}