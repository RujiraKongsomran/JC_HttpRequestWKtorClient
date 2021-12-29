package com.rujirakongsomran.jc_httprequestwktorclient.data.remote

import com.rujirakongsomran.jc_httprequestwktorclient.data.remote.dto.PostRequest
import com.rujirakongsomran.jc_httprequestwktorclient.data.remote.dto.PostResponse

interface PostsService {

    suspend fun getPosts(): List<PostResponse>

    suspend fun createPost(postRequest: PostRequest): PostResponse?
}