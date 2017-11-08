package kr.sofac.handsometalk.server;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import kr.sofac.handsometalk.dto.AuthorizationDTO;
import kr.sofac.handsometalk.dto.RegistrationDTO;
import kr.sofac.handsometalk.dto.UserDTO;
import kr.sofac.handsometalk.server.retrofit.ManagerRetrofit;
import kr.sofac.handsometalk.server.type.ServerResponse;
import timber.log.Timber;


/**
 * Created by Maxim on 03.08.2017.
 */

public class Connection<T> {

    private AnswerServerResponse answerServerResponse;

    public interface AnswerServerResponse<T> {
        void processFinish(Boolean isSuccess, ServerResponse<T> answerServerResponse);
    }

    /**
     * Authorization DTO
     */
    public void authorizationUser(AuthorizationDTO authorizationDTO, AnswerServerResponse<T> async) { //Change name request / Change data in method parameters
        answerServerResponse = async;
        new ManagerRetrofit<AuthorizationDTO>().sendRequest(authorizationDTO, new Object() {// Change type Object sending / Change data sending
        }.getClass().getEnclosingMethod().getName(), new ManagerRetrofit.AsyncAnswerString() {
            @Override
            public void processFinish(Boolean isSuccess, String answerString) {
                if (isSuccess) {
                    Type typeAnswer = new TypeToken<ServerResponse<String>>() { //Change type response
                    }.getType();
                    tryParsing(answerString, typeAnswer);
                } else {
                    answerServerResponse.processFinish(false, null);
                }
            }
        });
    }
    /**
     * Registration DTO
     */
    public void registrationUser(RegistrationDTO registrationDTO, AnswerServerResponse<T> async) { //Change name request / Change data in method parameters
        answerServerResponse = async;
        new ManagerRetrofit<RegistrationDTO>().sendRequest(registrationDTO, new Object() {// Change type Object sending / Change data sending
        }.getClass().getEnclosingMethod().getName(), new ManagerRetrofit.AsyncAnswerString() {
            @Override
            public void processFinish(Boolean isSuccess, String answerString) {
                if (isSuccess) {
                    Type typeAnswer = new TypeToken<ServerResponse<UserDTO>>() { //Change type response
                    }.getType();
                    tryParsing(answerString, typeAnswer);
                } else {
                    answerServerResponse.processFinish(false, null);
                }
            }
        });
    }

    /**
     * Get correct VERSION from Server
     */
//    public void getCorrectVersion(AnswerServerResponse<T> async) { //Change name request() / Change data in method parameters
//        answerServerResponse = async;
//        new ManagerRetrofit<String>().sendRequest("", new Object() {// Change type Object sending / Change data sending
//        }.getClass().getEnclosingMethod().getName(), (isSuccess, answerString) -> {
//            if (isSuccess) {
//                Type typeAnswer = new TypeToken<ServerResponse<AppVersionDTO>>() { //Change type response
//                }.getType();
//                tryParsing(answerString, typeAnswer);
//            } else {
//                answerServerResponse.processFinish(false, null);
//            }
//        });
//    }

    /**
     * Get List VERSIONS history from Server
     */
    public void getVersionsHistory(AnswerServerResponse<T> async) {

    }

    /**
     * Get MANAGER info from server
     */
//    public void getManagerInfo(Long idManager, AnswerServerResponse<T> async) {
//        answerServerResponse = async;
//        new ManagerRetrofit<Long>().sendRequest(idManager, new Object() {// Change type Object sending / Change data sending
//        }.getClass().getEnclosingMethod().getName(), (isSuccess, answerString) -> {
//            if (isSuccess) {
//                Type typeAnswer = new TypeToken<ServerResponse<ManagerDTO>>() { //Change type response
//                }.getType();
//                tryParsing(answerString, typeAnswer);
//            } else {
//                answerServerResponse.processFinish(false, null);
//            }
//        });
//    }

    /**
     * Get CUSTOMER info from server
     */
    public void getCustomerInfo(Long idCustomer, AnswerServerResponse<T> async) {
    }

    /**
     * Get STAFF info from server
     */
    public void getStaffInfo(Long idStaff, AnswerServerResponse<T> async) {
    }


    /**
     * Get SETTINGS from Server
     */
    public void getSettings() {

    }

//    public void getListComments(Long postId, AnswerServerResponse<T> async) {
//        answerServerResponse = async;
//        new ManagerRetrofit<Long>().sendRequest(postId, new Object() {// Change (type sending) / (data sending)
//        }.getClass().getEnclosingMethod().getName(), (isSuccess, answerString) -> {
//            if (isSuccess) {
//                Type typeAnswer = new TypeToken<ServerResponse<ArrayList<CommentDTO>>>() { //Change type response(тип ответа)
//                }.getType();
//                tryParsing(answerString, typeAnswer);
//            } else {
//                answerServerResponse.processFinish(false, null);
//            }
//        });
//    }

    /**   */
//    public void getListPosts(String stringTypeGroup, AnswerServerResponse<T> async) {
//        answerServerResponse = async;
//        new ManagerRetrofit<String>().sendRequest(stringTypeGroup, new Object() {// Change (type sending) / (data sending)
//        }.getClass().getEnclosingMethod().getName(), (isSuccess, answerString) -> {
//            if (isSuccess) {
//                Type typeAnswer = new TypeToken<ServerResponse<List<PostDTO>>>() { //Change type response(тип ответа)
//                }.getType();
//                tryParsing(answerString, typeAnswer);
//            } else {
//                answerServerResponse.processFinish(false, null);
//            }
//        });
//    }

    /**   */
//    public void createPost(Context context, PostDTO postDTO, ArrayList<Uri> listUri, AnswerServerResponse<T> async) {
//        answerServerResponse = async;
//
//        new ManagerRetrofit<PostDTO>().sendMultiPartRequest(postDTO, new Object() {// Change (type sending) / (data sending)
//        }.getClass().getEnclosingMethod().getName(), generateMultiPartList(listUri, context), (isSuccess, answerString) -> {
//            if (isSuccess) {
//                Type typeAnswer = new TypeToken<ServerResponse>() { //Change type response(тип ответа)
//                }.getType();
//                tryParsing(answerString, typeAnswer);
//            } else {
//                answerServerResponse.processFinish(false, null);
//            }
//        });
//
//    }


    /**   */
//    public void updatePost(Context context, PostDTO postDTO, ArrayList<Uri> listUri, ArrayList<String> listDeletingFiles, AnswerServerResponse<T> async) {
//        answerServerResponse = async;
//
//        new ManagerRetrofit<PostDTO>().sendMultiPartWithTwoObj(postDTO, new Object() {// Change (type sending) / (data sending)
//        }.getClass().getEnclosingMethod().getName(), generateMultiPartList(listUri, context), listDeletingFiles, (isSuccess, answerString) -> {
//            if (isSuccess) {
//                Type typeAnswer = new TypeToken<ServerResponse>() { //Change type response(тип ответа)
//                }.getType();
//                tryParsing(answerString, typeAnswer);
//            } else {
//                answerServerResponse.processFinish(false, null);
//            }
//        });
//    }

    /**   */
//    public void deletePost(PostDTO postDTO, AnswerServerResponse<T> async) {
//        answerServerResponse = async;
//        new ManagerRetrofit<Long>().sendRequest(postDTO.getId(), new Object() {// Change (type sending) / (data sending)
//        }.getClass().getEnclosingMethod().getName(), (isSuccess, answerString) -> {
//            if (isSuccess) {
//                Type typeAnswer = new TypeToken<ServerResponse>() { //Change type response(тип ответа)
//                }.getType();
//                tryParsing(answerString, typeAnswer);
//            } else {
//                answerServerResponse.processFinish(false, null);
//            }
//        });
//    }

    /**
     * COMMENT
     */
//    public void createComment(CommentDTO commentDTO, AnswerServerResponse<T> async) {
//        answerServerResponse = async;
//        new ManagerRetrofit<CommentDTO>().sendRequest(commentDTO, new Object() {// Change (type sending) / (data sending)
//        }.getClass().getEnclosingMethod().getName(), (isSuccess, answerString) -> {
//            if (isSuccess) {
//                Type typeAnswer = new TypeToken<ServerResponse>() { //Change type response(тип ответа)
//                }.getType();
//                tryParsing(answerString, typeAnswer);
//            } else {
//                answerServerResponse.processFinish(false, null);
//            }
//        });
//    }

    /** */
//    public void updateComment(CommentDTO commentDTO, AnswerServerResponse<T> async) {
//        answerServerResponse = async;
//        new ManagerRetrofit<CommentDTO>().sendRequest(commentDTO, new Object() {// Change (type sending) / (data sending)
//        }.getClass().getEnclosingMethod().getName(), (isSuccess, answerString) -> {
//            if (isSuccess) {
//                Type typeAnswer = new TypeToken<ServerResponse>() { //Change type response(тип ответа)
//                }.getType();
//                tryParsing(answerString, typeAnswer);
//            } else {
//                answerServerResponse.processFinish(false, null);
//            }
//        });
//    }

    /**   */
//    public void deleteComment(CommentDTO commentDTO, AnswerServerResponse<T> async) {
//        answerServerResponse = async;
//        new ManagerRetrofit<Long>().sendRequest(commentDTO.getId(), new Object() {// Change (type sending) / (data sending)
//        }.getClass().getEnclosingMethod().getName(), (isSuccess, answerString) -> {
//            if (isSuccess) {
//                Type typeAnswer = new TypeToken<ServerResponse>() { //Change type response(тип ответа)
//                }.getType();
//                tryParsing(answerString, typeAnswer);
//            } else {
//                answerServerResponse.processFinish(false, null);
//            }
//        });
//    }


    /**
     * Dop methods
     */

//    public ArrayList<MultipartBody.Part> generateMultiPartList(ArrayList<Uri> listFileUri, Context context) {
//        ArrayList<MultipartBody.Part> arrayListMulti = new ArrayList<>();
//        for (int i = 0; i < listFileUri.size(); i++) {
//            Timber.e("listFileUri.get(i).toString()  " + listFileUri.get(i).toString());
//            try {
//                File file = new File(PathUtil.getPath(context, listFileUri.get(i)));
//                arrayListMulti.add(MultipartBody.Part.createFormData("files[" + i + "]", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file)));
//            } catch (URISyntaxException e) {
//                e.printStackTrace();
//            }
//
//        }
//        return arrayListMulti;
//    }

    private void tryParsing(String answerString, Type typeAnswer) {
        try {
            answerServerResponse.processFinish(true, getObjectTransferFromJSON(answerString, typeAnswer));
        } catch (Exception e) {
            answerServerResponse.processFinish(false, null);
            e.printStackTrace();
        }
    }

    private ServerResponse<T> getObjectTransferFromJSON(String string, Type type) {
        try {
            return new Gson().fromJson(string, type);
        } catch (JsonSyntaxException e) {

            Timber.e("Не соответствующий тип данных для парсинга JSON");
            e.printStackTrace();
            return null;
        }
    }

}
