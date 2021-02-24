package com.rahulabrol.pocket52test

import androidx.annotation.MainThread
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.rahulabrol.pocket52test.base.LiveCoroutinesViewModel
import com.rahulabrol.pocket52test.model.Post
import com.rahulabrol.pocket52test.model.PostDetail
import com.rahulabrol.pocket52test.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Rahul Abrol on 24/2/21.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private var homeRepository: HomeRepository) :
    LiveCoroutinesViewModel() {

    var isLoading: ObservableBoolean = ObservableBoolean(false)
    var toastLiveData: MutableLiveData<String> = MutableLiveData()

    var postsLiveData: LiveData<List<Post>?>
    private var postMutualLiveData: MutableLiveData<Boolean> = MutableLiveData()

    var postDetailLiveData: LiveData<PostDetail?>
    private var postDetailMutualLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        postsLiveData = postMutualLiveData.switchMap {
            isLoading.set(it)
            launchOnViewModelScope {
                this.homeRepository.getPosts({ size ->
                    isLoading.set(false)
                }, {
                    isLoading.set(false)
                    toastLiveData.postValue(it)
                }).asLiveData()
            }
        }

        postDetailLiveData = postDetailMutualLiveData.switchMap {
            isLoading.set(true)
            launchOnViewModelScope {
                this.homeRepository.getPostDetail(it, {
                    isLoading.set(false)
                }, {
                    isLoading.set(false)
                    toastLiveData.postValue(it)
                }).asLiveData()
            }
        }
    }

    @MainThread
    fun getPostList() {
        postMutualLiveData.value = true
    }

    @MainThread
    fun getUserDetail(userId: String?) {
        postDetailMutualLiveData.value = userId
    }

}