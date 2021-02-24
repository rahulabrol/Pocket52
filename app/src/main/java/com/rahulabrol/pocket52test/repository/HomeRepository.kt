package com.rahulabrol.pocket52test.repository

import android.util.Log
import com.rahulabrol.pocket52test.model.OriginalPost
import com.rahulabrol.pocket52test.model.Post
import com.rahulabrol.pocket52test.model.PostDetail
import com.rahulabrol.pocket52test.network.PocketClient
import com.rahulabrol.pocket52test.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Rahul Abrol on 24/2/21.
 */
class HomeRepository @Inject constructor(private val pocketClient: PocketClient) {
    private val TAG = "HomeRepository"

    suspend fun getPosts(
        onSuccess: (Int) -> Unit,
        onError: (String) -> Unit
    ): Flow<List<Post>?> {
        return flow {
            try {
                val response: Response<List<OriginalPost>> = pocketClient.getPosts()
                Log.e(TAG, response.body().toString())
                if (response.isSuccessful) {
                    val map = mutableMapOf<String, Post>()
                    val data = response.body()
                    //club all the elements inside a map
                    data?.forEach {
                        if (map.containsKey(it.userId)) {
                            val post = map[it.userId]
                            post?.apply {
                                totalPosts = totalPosts?.plus(1)
                                postList?.add(it)
                            }
                        } else {
                            val list = arrayListOf<OriginalPost>()
                            list.add(it)
                            val post = Post(it.userId, 1, list)
                            map[it.userId!!] = post
                        }
                    }
                    val mainList = arrayListOf<Post>()
                    map.values.map {
                        mainList.add(it)
                    }
                    emit(mainList)
                    //return list size
                    onSuccess(mainList.size)

                } else {
                    // handle the case when the API request gets an error response.
                    // e.g. internal server error.
                    onError(Utils.parseError(response.errorBody())!!)
                }
            } catch (e: Exception) {
                // handle the case when the API request gets an exception response.
                // e.g. network connection error.
                e.message?.let { Log.e(TAG, it) }
                onError(Utils.parseException(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPostDetail(
        userId: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ): Flow<PostDetail?> {
        return flow {
            try {
                val response: Response<PostDetail> = pocketClient.getUserDetails(userId)
                Log.e(TAG, response.body().toString())
                if (response.isSuccessful) {
                    emit(response.body())
                    onSuccess()
                } else {
                    // handle the case when the API request gets an error response.
                    // e.g. internal server error.
                    onError(Utils.parseError(response.errorBody())!!)
                }
            } catch (e: Exception) {
                // handle the case when the API request gets an exception response.
                // e.g. network connection error.
                Log.e(TAG, e.message!!)
                onError(Utils.parseException(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}