package co.icanteach.projectx.data

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val httpUrl = request.url().newBuilder()
            .addQueryParameter(API_KEY_QUERY, API_KEY_VALUE)
            .build()

        request = request.newBuilder().url(httpUrl).build()

        return chain.proceed(request)
    }

    companion object {
        const val API_KEY_QUERY = "api_key"
        const val API_KEY_VALUE = "5d967c7c335764f39b1efbe9c5de9760"
    }
}