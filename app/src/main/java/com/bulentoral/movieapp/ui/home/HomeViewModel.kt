package com.bulentoral.movieapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulentoral.movieapp.model.MovieItem
import com.bulentoral.movieapp.network.ApiClient
import com.bulentoral.movieapp.util.Constant
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val movieList : MutableLiveData<List<MovieItem?>?> = MutableLiveData()
    val isLoading = MutableLiveData(false)
    val errorMessage : MutableLiveData<String?> = MutableLiveData()


    fun getMovieList(){
        isLoading.value = true // senkron

        viewModelScope.launch {
            try {
                val response = ApiClient.getClient().getMovieList(token = Constant.BEARER_TOKEN)
                if (response.isSuccessful){
                    movieList.postValue(response.body()?.movieItems) //Asenkron postvalue()
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