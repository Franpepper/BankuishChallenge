package com.fran.bankuishchallenge.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.fran.bankuishchallenge.databinding.ViewHolderItemBinding
import com.fran.bankuishchallenge.model.Repository
import com.fran.bankuishchallenge.model.SearchResponse
import com.fran.bankuishchallenge.ui.fragments.RepoFragmentDirections
import com.fran.bankuishchallenge.ui.viewHolders.RepoViewHolder

class RepoAdapter(private val repoList: List<Repository>): RecyclerView.Adapter<RepoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(ViewHolderItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = repoList[position]
        holder.bind(repo)

        holder.itemView.setOnClickListener{ view ->
            val action = RepoFragmentDirections.actionRepoFragmentToMoreInfoFragment(repo)
            view.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = repoList.size
}