package kr.sofac.handsometalk.model.server.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import kr.sofac.handsometalk.BASE_URL
import kr.sofac.handsometalk.model.server.type.ServerRequest
import kr.sofac.handsometalk.model.server.type.ServerResponse
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.IOException
import java.util.*

/**
 * Created by Maxim on 03.08.2017.
 */

class ManagerRetrofit<T> {

    //Константы
    private val serverResponseError = SERVER_RESPONSE_ERROR
    private val serverResponseSuccess = SERVER_RESPONSE_SUCCESS
    private var serverResponse = serverResponseError
    private val baseUrl = BASE_URL

    //Данные
    private var serviceRetrofit: ServiceRetrofit? = null
    private var serverRequest: ServerRequest? = null

    //Обработчики
    private var answerString: AsyncAnswerString? = null

    /**
     * Иницалиазация сервиса передачи
     */
    init {
        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create()
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        serviceRetrofit = retrofit.create(ServiceRetrofit::class.java)
    }

    /**
     * Интерфейсы обработки событий в потоке
     */
    interface AsyncAnswerString {
        fun processFinish(isSuccess: Boolean?, answerString: String?)
    }

    /**
     * Get Callback<ResponseBody> for this Request;
    </ResponseBody> */
    fun sendRequest(`object`: T, requestType: String, responseBodyCallback: Callback<ResponseBody>) {
        serverRequest = ServerRequest(requestType, `object`)
        logServerRequest(serverRequest)
        serviceRetrofit!!.getData(serverRequest!!).enqueue(responseBodyCallback)
    }

    /**
     * Get Callback<ResponseBody> for this Request;
    </ResponseBody> */
    fun sendMultiPartRequest(`object`: T, requestType: String, partArrayList: ArrayList<MultipartBody.Part>, asyncAnswer: AsyncAnswerString) {
        serverRequest = ServerRequest(requestType, `object`)
        answerString = asyncAnswer
        logServerRequest(serverRequest)

        val builder = GsonBuilder()
        val gson = builder.create()

        val serviceUploading = ServiceGenerator.createService(ServiceRetrofit::class.java)
        // finally, execute the request
        val call = serviceUploading.sendMultiPartRequest(
                RequestBody.create(
                        MediaType.parse("text/plain"),
                        gson.toJson(serverRequest)),
                partArrayList)

        call.enqueue(object : Callback<ResponseBody>() {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                try {
                    serverResponse = logServerResponse(response.body().toString())
                    if (serverResponseSuccess == getServerResponseStringFromJSON(serverResponse).responseStatus) {
                        answerString!!.processFinish(true, serverResponse)
                    } else {
                        answerString!!.processFinish(false, null)
                    }
                } catch (e: IOException) {
                    answerString!!.processFinish(false, null)
                    e.printStackTrace()
                }

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                answerString!!.processFinish(false, null)
            }
        })
    }

    /**
     * Get Callback<ResponseBody> for this Request;
    </ResponseBody> */
    fun sendMultiPartWithTwoObj(`object`: T, requestType: String, partArrayList: ArrayList<MultipartBody.Part>, listDeletingFiles: ArrayList<String>, asyncAnswer: AsyncAnswerString) {
        serverRequest = ServerRequest(requestType, `object`)
        answerString = asyncAnswer
        logServerRequest(serverRequest)

        val builder = GsonBuilder()
        val gson = builder.create()

        var stringDeleting = ""
        for (str in listDeletingFiles) {
            stringDeleting = stringDeleting + ";" + str
        }

        Timber.e("JSON DEL >>>>>>>>> " + stringDeleting)

        val serviceUploading = ServiceGenerator.createService(ServiceRetrofit::class.java)

        // finally, execute the request
        val call = serviceUploading.sendMultiPartWithTwoObj(
                RequestBody.create(
                        MediaType.parse("text/plain"),
                        gson.toJson(serverRequest)),
                RequestBody.create(
                        MediaType.parse("text/plain"),
                        stringDeleting),
                partArrayList)

        call.enqueue(object : Callback<ResponseBody>() {
            fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                try {
                    serverResponse = logServerResponse(response.body().string())
                    if (serverResponseSuccess == getServerResponseStringFromJSON(serverResponse).responseStatus) {
                        answerString!!.processFinish(true, serverResponse)
                    } else {
                        answerString!!.processFinish(false, null)
                    }
                } catch (e: IOException) {
                    answerString!!.processFinish(false, null)
                    e.printStackTrace()
                }

            }

            fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                answerString!!.processFinish(false, null)
            }
        })
    }

    /**
     * String
     * Get AsyncAnswer with True(SUCCESS) or False(ERROR) and String <= json (body response) for this Request;
     */
    fun sendRequest(`object`: T, requestType: String, asyncAnswer: AsyncAnswerString) {
        answerString = asyncAnswer

        sendRequest(`object`, requestType, object : Callback<ResponseBody>() {
            fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                try {
                    serverResponse = logServerResponse(response.body().string())
                    if (serverResponseSuccess == getServerResponseStringFromJSON(serverResponse).responseStatus) {
                        answerString!!.processFinish(true, serverResponse)
                    } else {
                        answerString!!.processFinish(false, null)
                    }
                } catch (e: IOException) {
                    answerString!!.processFinish(false, null)
                    e.printStackTrace()
                }

            }

            fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                answerString!!.processFinish(false, null)
                t.printStackTrace()
            }
        })
    }


    /**
     * //////// Вспомогательные методы ////////
     */

    /**
     * Получение ServerResponse из String JSON
     */
    private fun getServerResponseStringFromJSON(stringJSON: String): ServerResponse<String> {
        val typeServerResponse = object : TypeToken<ServerResponse<*>>() {

        }.getType()
        try {
            return Gson().fromJson(stringJSON, typeServerResponse)
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
        }

        return ServerResponse(serverResponseError, "")
    }


    /**
     * Логирование данных передачи
     */
    private fun logServerRequest(serverRequest: ServerRequest) {
        val builder = GsonBuilder()
        val gson = builder.create()
        Timber.e(">>>>>>>>>>>>>>>>> \n" + gson.toJson(serverRequest))
    }

    /**
     * Логирование данных приема
     */
    private fun logServerResponse(serverResponse: String): String {
        Timber.e("<<<<<<<<<<<<<<<< \n" + serverResponse)
        return serverResponse
    }

    companion object {

        private val SERVER_RESPONSE_ERROR = "SERVER_RESPONSE_ERROR"
        private val SERVER_RESPONSE_SUCCESS = "SERVER_RESPONSE_SUCCESS"
    }

}
