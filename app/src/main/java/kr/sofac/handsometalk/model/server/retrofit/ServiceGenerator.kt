package kr.sofac.handsometalk.model.server.retrofit

import com.google.gson.GsonBuilder
import kr.sofac.handsometalk.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Maxim on 28.09.2017.
 */

object ServiceGenerator {

    private val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create()
    private val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))

    private val retrofit = builder.build()

    private val httpClient = OkHttpClient.Builder()

    fun <S> createService(
            serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}
