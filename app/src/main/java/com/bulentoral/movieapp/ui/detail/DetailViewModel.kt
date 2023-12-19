package com.bulentoral.movieapp.ui.detail

import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulentoral.movieapp.model.MovieDetalResponse
import com.bulentoral.movieapp.network.ApiClient
import com.bulentoral.movieapp.util.Constant
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    val movieDetail : MutableLiveData<MovieDetalResponse> = MutableLiveData()
    val isLoading = MutableLiveData(false)
    val errorMessage : MutableLiveData<String?> = MutableLiveData()


    fun getMovieDetail(movieID: Int){
        isLoading.value = true // senkron

        viewModelScope.launch {
            try {
                val response = ApiClient.getClient().getMovieDetail(movieID = movieID.toString() ,token = Constant.BEARER_TOKEN)
                if (response.isSuccessful){
                    movieDetail.postValue(response.body()) //Asenkron postvalue()
                }
                else {
                    if (response.message().isNullOrEmpty()){
                        errorMessage.value = "An unkonown error occurred"
                    }
                    else {
                        errorMessage.value = response.message()
                    }
                }

            }
            catch (e:Exception) {
                errorMessage.value = e.message
            }
            finally {
                isLoading.value = false
            }
        }
    }
}