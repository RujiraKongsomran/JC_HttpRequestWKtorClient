package com.rujirakongsomran.jc_httprequestwktorclient.data.remote

import com.rujirakongsomran.jc_httprequestwktorclient.data.remote.dto.PostRequest
import com.rujirakongsomran.jc_httprequestwktorclient.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.lang.Exception

class PostsServiceImpl(
    private val client: HttpClient
) : PostsService {
    override suspend fun getPosts(): List<PostResponse> = try {
        client.get { url(HttpRoutes.POSTS) }
    } catch (e: RedirectResponseException) {
        // 3xx - response
        println("Error: ${e.response.status.description}")
        emptyList()
    } catch (e: ClientRequestException) {
        // 4xx - response
        println("Error: ${e.response.status.description}")
        emptyList()
    } catch (e: ServerResponseException) {
        // 5xx - response
        println("Error: ${e.response.status.description}")
        emptyList()
    } catch (e: Exception) {
        println("Error: ${e.message}")
        emptyList()
    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse? = try {
        client.post<PostResponse> {
            url(HttpRoutes.POSTS)
            contentType(ContentType.Application.Json)
            body = postRequest
        }
    } catch (e: RedirectResponseException) {
        // 3xx - response
        println("Error: ${e.response.status.description}")
        null
    } catch (e: ClientRequestException) {
        // 4xx - response
        println("Error: ${e.response.status.description}")
        null
    } catch (e: ServerResponseException) {
        // 5xx - response
        println("Error: ${e.response.status.description}")
        null
    } catch (e: Exception) {
        println("Error: ${e.message}")
        null
    }
}