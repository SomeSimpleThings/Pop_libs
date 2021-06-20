package com.somethingsimple.poplibs.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.somethingsimple.poplibs.data.user.model.GithubUser
import com.somethingsimple.poplibs.databinding.UserItemBinding

class GithubUsersAdapter(val presenter: IUsersListPresenter) :
    RecyclerView.Adapter<GithubUsersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root),
        UserItemView {
        override fun bind(user: GithubUser) {
            with(binding) {
                Glide
                    .with(itemView.context)
                    .load(user.avatarUrl)
                    .centerCrop()
                    .into(userAvatar)
                userLogin.text = user.login
                userName.text = user.name
                email.text = user.email
                company.text = user.company
                location.text = user.location
                reposCount.text = user.publicRepos.toString()
                followersCount.text = user.followers.toString()
                followingCount.text = user.following.toString()
            }
        }

        override var pos = -1


    }

}