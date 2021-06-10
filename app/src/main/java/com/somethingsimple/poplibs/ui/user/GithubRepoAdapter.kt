package com.somethingsimple.poplibs.ui.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.somethingsimple.poplibs.data.repo.model.GithubRepo
import com.somethingsimple.poplibs.databinding.RepoItemBinding
import com.somethingsimple.poplibs.ui.repos.RepoItemView
import com.somethingsimple.poplibs.ui.repos.RepoListPresenter

class GithubRepoAdapter(val presenter: RepoListPresenter) :
    RecyclerView.Adapter<GithubRepoAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            RepoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }

    inner class ViewHolder(val binding: RepoItemBinding) : RecyclerView.ViewHolder(binding.root),
        RepoItemView {
        override fun bind(repo: GithubRepo) {
            with(binding) {
                repoName.text = repo.name
                description.text = repo.description
                language.text = repo.language
                issuesCount.text = repo.openIssues.toString()
                watchersCount.text = repo.watchersCount.toString()
            }
        }

        override var pos = -1

    }
}