package com.rahulabrol.pocket52test.network

import javax.inject.Inject

/**
 * Created by Rahul Abrol on 24/2/21.
 */
class PocketClient  @Inject  constructor(private val pocketService: PocketService) {
    suspend fun getPosts() = pocketService.getPosts()
    suspend fun getUserDetails(userId: String) = pocketService.getUserDetails(userId)
}