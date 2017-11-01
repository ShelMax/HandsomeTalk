package kr.sofac.handsometalk.model.server.retrofit


import kr.sofac.handsometalk.model.server.type.ServerRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.util.*

/**
 * Created by Maxim on 03.08.2017.
 */

interface ServiceRetrofit {

    @POST("1111111111")
    fun getData(@Body serverRequest: ServerRequest): Call<ResponseBody>

    @Multipart
    @POST("!!!!!!!!!!!")
    fun sendMultiPartRequest(@Part("json") obj: RequestBody, @Part file: ArrayList<MultipartBody.Part>): Call<ResponseBody>

    @Multipart
    @POST("!!!!!!!!!!")
    fun sendMultiPartWithTwoObj(@Part("json") obj: RequestBody, @Part("deleteFiles") listDeleteFiles: RequestBody, @Part file: ArrayList<MultipartBody.Part>): Call<ResponseBody>

}