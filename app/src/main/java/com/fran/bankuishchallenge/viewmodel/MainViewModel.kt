package com.fran.bankuishchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fran.bankuishchallenge.domain.data.ApiClient
import com.fran.bankuishchallenge.domain.data.HttpStatus
import com.fran.bankuishchallenge.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val client: ApiClient
    ) : ViewModel() {

   private val _status = MutableLiveData<HttpStatus>()
   val status: LiveData<HttpStatus>
       get() = _status

   private val _repoList = MutableLiveData<List<Repository>>()
   val repoList: MutableLiveData<List<Repository>>
       get() = _repoList

   fun getRepoList(query: String){
        viewModelScope.launch {
            try{
                val response = client.getRepos(query)
                if(response.isSuccessful){
                   _repoList.value = response.body()?.items
                }else{
                    throw HttpException(response)
                }
            } catch (throwable : Throwable){
                _repoList.value = emptyList()
                when(throwable){
                    is IOException -> _status.value = HttpStatus.IOException
                    is HttpException -> when(throwable.code()){
                        in 400..499 -> _status.value = HttpStatus.HTTP400
                        in 500..599 -> _status.value = HttpStatus.HTTP500
                        else -> _status.value = HttpStatus.GenericError
                    }
                }
            }
        }
    }
}