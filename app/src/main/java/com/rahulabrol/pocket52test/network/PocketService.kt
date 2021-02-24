package com.rahulabrol.pocket52test.network

import com.rahulabrol.pocket52test.model.PostDetail
import com.rahulabrol.pocket52test.model.OriginalPost
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Rahul Abrol on 24/2/21.
 */
interface PocketService {

    @GET("posts/")
    suspend fun getPosts(): Response<List<OriginalPost>>

    @GET("users/{user_id}/")
    suspend fun getUserDetails(
        @Path(value = "user_id", encoded = true) userId: String
    ): Response<PostDetail>
}