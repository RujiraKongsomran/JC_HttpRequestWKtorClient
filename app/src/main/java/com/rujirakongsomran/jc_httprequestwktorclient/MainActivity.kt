package com.rujirakongsomran.jc_httprequestwktorclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rujirakongsomran.jc_httprequestwktorclient.data.remote.PostsService
import com.rujirakongsomran.jc_httprequestwktorclient.data.remote.dto.PostResponse
import com.rujirakongsomran.jc_httprequestwktorclient.ui.theme.JC_HttpRequestWKtorClientTheme


class MainActivity : ComponentActivity() {

    private val service = PostsService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val posts = produceState<List<PostResponse>>(
                initialValue = emptyList(),
                producer = {
                    value = service.getPosts()
                }
            )
            JC_HttpRequestWKtorClientTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    LazyColumn {
                        items(posts.value) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(text = it.title, fontSize = 20.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = it.body, fontSize = 14.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
