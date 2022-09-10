package com.fran.bankuishchallenge.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.fran.bankuishchallenge.databinding.FragmentMoreInfoBinding
import com.fran.bankuishchallenge.model.Repository
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreInfoFragment : Fragment() {

    private var _binding: FragmentMoreInfoBinding? = null
    private val binding get() = _binding!!
    private val args: MoreInfoFragmentArgs by navArgs()
    private lateinit var repo: Repository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repo = args.repoData
        (activity as AppCompatActivity).supportActionBar?.title = repo.name
        fillData()
    }

    private fun fillData(){
        binding.apply {
            tvName.text = repo.name
            tvAuthor.text = repo.owner.login
            tvDescription.text = repo.description
            tvStars.text = repo.stargazersCount.toString()
            tvForks.text = repo.forksCount.toString()
            tvHomePage.text = repo.owner.url
            tvHomePage.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(repo.owner.url)
                startActivity(intent)
            }
            Glide.with(requireContext())
                .load(repo.owner.avatarUrl)
                .into(ivAvatar)
        }
    }

}