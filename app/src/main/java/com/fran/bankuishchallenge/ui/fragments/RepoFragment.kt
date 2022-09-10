package com.fran.bankuishchallenge.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.fran.bankuishchallenge.R
import com.fran.bankuishchallenge.databinding.FragmentRepoBinding
import com.fran.bankuishchallenge.domain.data.HttpStatus
import com.fran.bankuishchallenge.model.Repository
import com.fran.bankuishchallenge.ui.adapters.RepoAdapter
import com.fran.bankuishchallenge.viewmodel.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RepoFragment : Fragment() {

    private var _binding: FragmentRepoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()


    private lateinit var adapter : RepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoBinding.inflate(inflater, container, false)
        val swipe = binding.swipeRefresh
        swipe.setOnRefreshListener {
            requestRepo()
            Toast.makeText(context, R.string.dialog_refreshed, Toast.LENGTH_SHORT).show()
            swipe.isRefreshing = false
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestRepo()
    }

    private fun requestRepo(){
        setupView()
    }

    private fun setupView(){
        viewModel.getRepoList("kotlin")
        setupObserver()
    }

    private fun setupObserver(){
        viewModel.repoList.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                loadRepos(it)
            }else{
                showDialog()
            }
        }
    }

    private fun loadRepos(data: List<Repository>){
        adapter = RepoAdapter(data)
        binding.rvMain.adapter = adapter

    }

    private fun showDialog(){
        var message : String
        viewModel.status.observe(viewLifecycleOwner){ it ->
            message = when(it){
                HttpStatus.GenericError -> getString(R.string.dialog_generic_error)
                HttpStatus.HTTP400 -> getString(R.string.dialog_http_400)
                HttpStatus.HTTP500 -> getString(R.string.dialog_http_500)
                HttpStatus.IOException -> getString(R.string.dialog_io_exception)
            }
            context?.let{
                MaterialAlertDialogBuilder(it)
                    .setTitle(getString(R.string.status_dialog))
                    .setMessage(message)
                    .setPositiveButton(getString(R.string.dialog_retry_button)){ dialog, _ ->
                        dialog.cancel()
                        requestRepo()
                    }
                    .setNegativeButton(getString(R.string.dialog_negative_button)){ dialog, _ ->
                        dialog.cancel()
                    }
                    .show()
            }
        }
    }

}