package kr.sofac.handsometalk.model.server

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kr.sofac.handsometalk.model.server.type.ServerResponse
import timber.log.Timber
import java.lang.reflect.Type


/**
 * Created by Maxim on 03.08.2017.
 */

class Connection<T> {

    private var answerServerResponse: AnswerServerResponse<*>? = null

    interface AnswerServerResponse<T> {
        fun processFinish(isSuccess: Boolean?, answerServerResponse: ServerResponse<T>?)
    }

    /**
     * Authorization DTO
     */
//    fun authorizationUser(authorizationDTO: AuthorizationDTO, async: AnswerServerResponse<T>) { //Change name request / Change data in method parameters
//        answerServerResponse = async
//        ManagerRetrofit<AuthorizationDTO>().sendRequest(authorizationDTO, object : Any() {// Change type Object sending / Change data sending
//
//        }.javaClass.enclosingMethod.name, { isSuccess, answerString ->
//            if (isSuccess!!) {
//                val typeAnswer = object : TypeToken<ServerResponse<UserDTO>>() { //Change type response
//
//                }.getType()
//                tryParsing(answerString, typeAnswer)
//            } else {
//                answerServerResponse!!.processFinish(false, null)
//            }
//        })
//    }
//
//    /**
//     * Get correct VERSION from Server
//     */
//    fun getCorrectVersion(async: AnswerServerResponse<T>) { //Change name request() / Change data in method parameters
//        answerServerResponse = async
//        ManagerRetrofit<String>().sendRequest("", object : Any() {// Change type Object sending / Change data sending
//
//        }.javaClass.enclosingMethod.name, { isSuccess, answerString ->
//            if (isSuccess!!) {
//                val typeAnswer = object : TypeToken<ServerResponse<AppVersionDTO>>() { //Change type response
//
//                }.getType()
//                tryParsing(answerString, typeAnswer)
//            } else {
//                answerServerResponse!!.processFinish(false, null)
//            }
//        })
//    }

    /**
     * Get List VERSIONS history from Server
     */
    fun getVersionsHistory(async: AnswerServerResponse<T>) {

    }

    /**
     * Get MANAGER info from server
     */
//    fun getManagerInfo(idManager: Long?, async: AnswerServerResponse<T>) {
//        answerServerResponse = async
//        ManagerRetrofit<Long>().sendRequest(idManager, object : Any() {// Change type Object sending / Change data sending
//
//        }.javaClass.enclosingMethod.name, { isSuccess, answerString ->
//            if (isSuccess!!) {
//                val typeAnswer = object : TypeToken<ServerResponse<ManagerDTO>>() { //Change type response
//
//                }.getType()
//                tryParsing(answerString, typeAnswer)
//            } else {
//                answerServerResponse!!.processFinish(false, null)
//            }
//        })
//    }

    /**
     * Get CUSTOMER info from server
     */
    fun getCustomerInfo(idCustomer: Long?, async: AnswerServerResponse<T>) {}

    /**
     * Get STAFF info from server
     */
    fun getStaffInfo(idStaff: Long?, async: AnswerServerResponse<T>) {}


    /**
     * Get SETTINGS from Server
     */
    fun getSettings() {

    }

//    fun getListComments(postId: Long?, async: AnswerServerResponse<T>) {
//        answerServerResponse = async
//        ManagerRetrofit<Long>().sendRequest(postId, object : Any() {// Change (type sending) / (data sending)
//
//        }.javaClass.enclosingMethod.name, { isSuccess, answerString ->
//            if (isSuccess!!) {
//                val typeAnswer = object : TypeToken<ServerResponse<ArrayList<CommentDTO>>>() { //Change type response(тип ответа)
//
//                }.getType()
//                tryParsing(answerString, typeAnswer)
//            } else {
//                answerServerResponse!!.processFinish(false, null)
//            }
//        })
//    }
//
//    /**    */
//    fun getListPosts(stringTypeGroup: String, async: AnswerServerResponse<T>) {
//        answerServerResponse = async
//        ManagerRetrofit<String>().sendRequest(stringTypeGroup, object : Any() {// Change (type sending) / (data sending)
//
//        }.javaClass.enclosingMethod.name, { isSuccess, answerString ->
//            if (isSuccess!!) {
//                val typeAnswer = object : TypeToken<ServerResponse<List<PostDTO>>>() { //Change type response(тип ответа)
//
//                }.getType()
//                tryParsing(answerString, typeAnswer)
//            } else {
//                answerServerResponse!!.processFinish(false, null)
//            }
//        })
//    }
//
//    /**    */
//    fun createPost(context: Context, postDTO: PostDTO, listUri: ArrayList<Uri>, async: AnswerServerResponse<T>) {
//        answerServerResponse = async
//
//        ManagerRetrofit<PostDTO>().sendMultiPartRequest(postDTO, object : Any() {// Change (type sending) / (data sending)
//
//        }.javaClass.enclosingMethod.name, generateMultiPartList(listUri, context), { isSuccess, answerString ->
//            if (isSuccess!!) {
//                val typeAnswer = object : TypeToken<ServerResponse<*>>() { //Change type response(тип ответа)
//
//                }.getType()
//                tryParsing(answerString, typeAnswer)
//            } else {
//                answerServerResponse!!.processFinish(false, null)
//            }
//        })
//
//    }
//
//
//    /**    */
//    fun updatePost(context: Context, postDTO: PostDTO, listUri: ArrayList<Uri>, listDeletingFiles: ArrayList<String>, async: AnswerServerResponse<T>) {
//        answerServerResponse = async
//
//        ManagerRetrofit<PostDTO>().sendMultiPartWithTwoObj(postDTO, object : Any() {// Change (type sending) / (data sending)
//
//        }.javaClass.enclosingMethod.name, generateMultiPartList(listUri, context), listDeletingFiles, { isSuccess, answerString ->
//            if (isSuccess!!) {
//                val typeAnswer = object : TypeToken<ServerResponse<*>>() { //Change type response(тип ответа)
//
//                }.getType()
//                tryParsing(answerString, typeAnswer)
//            } else {
//                answerServerResponse!!.processFinish(false, null)
//            }
//        })

        //        new ManagerRetrofit<PostDTO>().sendRequest(postDTO, new Object() {// Change (type sending) / (data sending)
        //        }.getClass().getEnclosingMethod().getName(), new ManagerRetrofit.AsyncAnswerString() {
        //            @Override
        //            public void processFinish(Boolean isSuccess, String answerString) {
        //                if (isSuccess) {
        //                    Type typeAnswer = new TypeToken<ServerResponse>() { //Change type response(тип ответа)
        //                    }.getType();
        //                    tryParsing(answerString, typeAnswer);
        //                } else {
        //                    answerServerResponse.processFinish(false, null);
        //                }
        //            }
        //        });
    //}

//    /**    */
//    fun deletePost(postDTO: PostDTO, async: AnswerServerResponse<T>) {
//        answerServerResponse = async
//        ManagerRetrofit<Long>().sendRequest(postDTO.getId(), object : Any() {// Change (type sending) / (data sending)
//
//        }.javaClass.enclosingMethod.name, { isSuccess, answerString ->
//            if (isSuccess!!) {
//                val typeAnswer = object : TypeToken<ServerResponse<*>>() { //Change type response(тип ответа)
//
//                }.getType()
//                tryParsing(answerString, typeAnswer)
//            } else {
//                answerServerResponse!!.processFinish(false, null)
//            }
//        })
//    }
//
//    /**
//     * COMMENT
//     */
//    fun createComment(commentDTO: CommentDTO, async: AnswerServerResponse<T>) {
//        answerServerResponse = async
//        ManagerRetrofit<CommentDTO>().sendRequest(commentDTO, object : Any() {// Change (type sending) / (data sending)
//
//        }.javaClass.enclosingMethod.name, { isSuccess, answerString ->
//            if (isSuccess!!) {
//                val typeAnswer = object : TypeToken<ServerResponse<*>>() { //Change type response(тип ответа)
//
//                }.getType()
//                tryParsing(answerString, typeAnswer)
//            } else {
//                answerServerResponse!!.processFinish(false, null)
//            }
//        })
//    }
//
//    /**  */
//    fun updateComment(commentDTO: CommentDTO, async: AnswerServerResponse<T>) {
//        answerServerResponse = async
//        ManagerRetrofit<CommentDTO>().sendRequest(commentDTO, object : Any() {// Change (type sending) / (data sending)
//
//        }.javaClass.enclosingMethod.name, { isSuccess, answerString ->
//            if (isSuccess!!) {
//                val typeAnswer = object : TypeToken<ServerResponse<*>>() { //Change type response(тип ответа)
//
//                }.getType()
//                tryParsing(answerString, typeAnswer)
//            } else {
//                answerServerResponse!!.processFinish(false, null)
//            }
//        })
//    }
//
//    /**    */
//    fun deleteComment(commentDTO: CommentDTO, async: AnswerServerResponse<T>) {
//        answerServerResponse = async
//        ManagerRetrofit<Long>().sendRequest(commentDTO.getId(), object : Any() {// Change (type sending) / (data sending)
//
//        }.javaClass.enclosingMethod.name, { isSuccess, answerString ->
//            if (isSuccess!!) {
//                val typeAnswer = object : TypeToken<ServerResponse<*>>() { //Change type response(тип ответа)
//
//                }.getType()
//                tryParsing(answerString, typeAnswer)
//            } else {
//                answerServerResponse!!.processFinish(false, null)
//            }
//        })
//    }


    /**
     * Dop methods
     */

//    fun generateMultiPartList(listFileUri: ArrayList<Uri>, context: Context): ArrayList<MultipartBody.Part> {
//        val arrayListMulti = ArrayList<MultipartBody.Part>()
//        for (i in listFileUri.indices) {
//            Timber.e("listFileUri.get(i).toString()  " + listFileUri[i].toString())
//            try {
//                val file = File(PathUtil.getPath(context, listFileUri[i]))
//                arrayListMulti.add(MultipartBody.Part.createFormData("files[$i]", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file)))
//            } catch (e: URISyntaxException) {
//                e.printStackTrace()
//            }
//
//        }
//        return arrayListMulti
//    }

    private fun tryParsing(answerString: String, typeAnswer: Type) {
        try {
            answerServerResponse!!.processFinish(true, getObjectTransferFromJSON(answerString, typeAnswer))
        } catch (e: Exception) {
            answerServerResponse!!.processFinish(false, null)
            e.printStackTrace()
        }

    }

    private fun getObjectTransferFromJSON(string: String, type: Type): ServerResponse<T>? {
        try {
            return Gson().fromJson(string, type)
        } catch (e: JsonSyntaxException) {

            Timber.e("Не соответствующий тип данных для парсинга JSON")
            e.printStackTrace()
            return null
        }

    }

}
