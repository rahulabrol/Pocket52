package com.rahulabrol.pocket52test.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Created by Rahul Abrol on 24/2/21.
 */
@JsonClass(generateAdapter = true)
class APIError {
    @field:Json(name = "message")
    val message: String? = null
}

@JsonClass(generateAdapter = true)
@Parcelize
data class OriginalPost(
    @field:Json(name = "userId") val userId: String? = null,
    @field:Json(name = "id") val id: String? = null,
    @field:Json(name = "title") val title: String? = null,
    @field:Json(name = "body") val body: String? = null
) : Parcelable

@Parcelize
data class Post(
    var id: String? = null,
    var totalPosts: Int? = null,
    var postList: ArrayList<OriginalPost>? = null
) : Parcelable

@JsonClass(generateAdapter = true)
data class PostDetail(
    @field:Json(name = "id") val id: String? = null,
    @field:Json(name = "name") val name: String? = null,
    @field:Json(name = "username") val username: String? = null,
    @field:Json(name = "email") val email: String? = null,
    @field:Json(name = "address") val address: Any? = null,
    @field:Json(name = "phone") val phone: String? = null,
    @field:Json(name = "website") val website: String? = null,
    @field:Json(name = "company") val company: Any? = null
)