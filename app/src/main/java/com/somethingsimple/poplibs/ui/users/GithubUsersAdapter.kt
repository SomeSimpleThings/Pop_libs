package com.somethingsimple.poplibs.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        override var pos = -1

        override fun setLogin(text: String) = with(binding) {
            userLogin.text = text
        }
    }

}