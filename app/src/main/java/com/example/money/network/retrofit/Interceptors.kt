package  com.example.money.network.retrofit

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * AuthInterceptor used to send access token as header
 * @author Mohamed Ibrahim
 * @param accessToken user secure token to access api
 */
class AuthInterceptor(private val accessToken: String?) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request = original.newBuilder()
            .header("Authorization", "Bearer ${this.accessToken!!}")
            .method(original.method(), original.body())
            .build()

        return chain.proceed(request)
    }
}

/**
 * APInterceptor used to send accept type as header to api
 * @author Mohamed Ibrahim
 */
class APInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request = original.newBuilder()
            .header("Accept", "application/json")
            .method(original.method(), original.body())
            .build()

        return chain.proceed(request)
    }
}

/**
 * APIContentInterceptor used to send content type as header to api
 * @author Mohamed Ibrahim
 */
class APIContentInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request = original.newBuilder()
            .header("Content-Type", "application/json")
            .method(original.method(), original.body())
            .build()

        return chain.proceed(request)
    }
}