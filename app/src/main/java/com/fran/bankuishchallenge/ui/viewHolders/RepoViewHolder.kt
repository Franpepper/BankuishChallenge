package com.fran.bankuishchallenge.ui.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.fran.bankuishchallenge.databinding.ViewHolderItemBinding
import com.fran.bankuishchallenge.model.Repository

class RepoViewHolder(private val binding: ViewHolderItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(repository: Repository) {
        binding.tvRepoName.text = repository.name
        binding.tvAuthor.text = repository.owner.login
        binding.cvMain.isClickable = true
    }
}