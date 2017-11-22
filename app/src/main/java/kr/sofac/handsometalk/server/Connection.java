package kr.sofac.handsometalk.server;

import android.content.Context;
import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.ArrayList;

import kr.sofac.handsometalk.dto.AuthorizationDTO;
import kr.sofac.handsometalk.dto.EstimateDTO;
import kr.sofac.handsometalk.dto.EventDTO;
import kr.sofac.handsometalk.dto.FeedbackDTO;
import kr.sofac.handsometalk.dto.GetEstimationsDTO;
import kr.sofac.handsometalk.dto.GetPushDTO;
import kr.sofac.handsometalk.dto.MessageDTO;
import kr.sofac.handsometalk.dto.NewEstimateRequestDTO;
import kr.sofac.handsometalk.dto.PushDTO;
import kr.sofac.handsometalk.dto.RegistrationDTO;
import kr.sofac.handsometalk.dto.UserDTO;
import kr.sofac.handsometalk.server.retrofit.ManagerRetrofit;
import kr.sofac.handsometalk.server.type.ServerResponse;
import kr.sofac.handsometalk.util.PathUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
        }.getClass().getEnclosingMethod().getName(), (isSuccess, answerString) -> {
            if (isSuccess) {
                Type typeAnswer = new TypeToken<ServerResponse<UserDTO>>() { //Change type response
                }.getType();
                tryParsing(answerString, typeAnswer);
            } else {
                answerServerResponse.processFinish(false, null);
            }
        });
    }

    /**
     * Registration DTO
     */
    public void registrationUser(RegistrationDTO registrationDTO, AnswerServerResponse<T> async) { //Change name request / Change data in method parameters
        answerServerResponse = async;
        new ManagerRetrofit<RegistrationDTO>().sendRequest(registrationDTO, new Object() {// Change type Object sending / Change data sending
        }.getClass().getEnclosingMethod().getName(), (isSuccess, answerString) -> {
            if (isSuccess) {
                Type typeAnswer = new TypeToken<ServerResponse<UserDTO>>() { //Change type response
                }.getType();
                tryParsing(answerString, typeAnswer);
            } else {
                answerServerResponse.processFinish(false, null);
            }
        });
    }

    /**
     * Get All Events
     */
    public void allEvents(String str, AnswerServerResponse<T> async) { //Change name request / Change data in method parameters
        answerServerResponse = async;
        new ManagerRetrofit<String>().sendRequest(str, new Object() {// Change type Object sending / Change data sending
        }.getClass().getEnclosingMethod().getName(), (isSuccess, answerString) -> {
            if (isSuccess) {
                Type typeAnswer = new TypeToken<ServerResponse<ArrayList<EventDTO>>>() { //Change type response
                }.getType();
                tryParsing(answerString, typeAnswer);
            } else {
                answerServerResponse.processFinish(false, null);
            }
        });
    }

    /**
     * Get All Push notification
     */
    public void getListPush(GetPushDTO getPushDTO, AnswerServerResponse<T> async) { //Change name request / Change data in method parameters
        answerServerResponse = async;
        new ManagerRetrofit<GetPushDTO>().sendRequest(getPushDTO, new Object() {// Change type Object sending / Change data sending
        }.getClass().getEnclosingMethod().getName(), (isSuccess, answerString) -> {
            if (isSuccess) {
                Type typeAnswer = new TypeToken<ServerResponse<ArrayList<PushDTO>>>() { //Change type response
                }.getType();
                tryParsing(answerString, typeAnswer);
            } else {
                answerServerResponse.processFinish(false, null);
            }
        });
    }

    /**
     * Get All Estimations
     */
    public void getEstimations(GetEstimationsDTO getEstimationDTO, AnswerServerResponse<T> async) { //Change name request / Change data in method parameters
        answerServerResponse = async;
        new ManagerRetrofit<GetEstimationsDTO>().sendRequest(getEstimationDTO, new Object() {// Change type Object sending / Change data sending
        }.getClass().getEnclosingMethod().getName(), (isSuccess, answerString) -> {
            if (isSuccess) {
                Type typeAnswer = new TypeToken<ServerResponse<ArrayList<EstimateDTO>>>() { //Change type response
                }.getType();
                tryParsing(answerString, typeAnswer);
            } else {
                answerServerResponse.processFinish(false, null);
            }
        });
    }

    /**
     * Get Estimation and All Message inside
     */
    public void getEstimation(MessageDTO messageDTO, AnswerServerResponse<T> async) { //Change name request / Change data in method parameters
        answerServerResponse = async;
        new ManagerRetrofit<MessageDTO>().sendRequest(messageDTO, new Object() {// Change type Object sending / Change data sending
        }.getClass().getEnclosingMethod().getName(), (isSuccess, answerString) -> {
            if (isSuccess) {
                Type typeAnswer = new TypeToken<ServerResponse<EstimateDTO>>() { //Change type response
                }.getType();
                tryParsing(answerString, typeAnswer);
            } else {
                answerServerResponse.processFinish(false, null);
            }
        });
    }

    //{"dataTransferObject":{"estimate_id":"1"}, "requestType":"getEstimation"}

    public void newEstimation(Context context, NewEstimateRequestDTO newEstimateRequestDTO, ArrayList<Uri> listUri, AnswerServerResponse<T> async) {
        answerServerResponse = async;

        new ManagerRetrofit<NewEstimateRequestDTO>().sendMultiPartRequest(newEstimateRequestDTO, new Object() {// Change (type sending) / (data sending)
        }.getClass().getEnclosingMethod().getName(), generateMultiPartList(listUri, context), new ManagerRetrofit.AsyncAnswerString() {
            @Override
            public void processFinish(Boolean isSuccess, String answerString) {
                if (isSuccess) {
                    Type typeAnswer = new TypeToken<ServerResponse>() { //Change type response(тип ответа)
                    }.getType();
                    tryParsing(answerString, typeAnswer);
                } else {
                    answerServerResponse.processFinish(false, null);
                }
            }
        });

    }

    public void newMessage(Context context, MessageDTO messageDTO, ArrayList<Uri> listUri, AnswerServerResponse<T> async) {
        answerServerResponse = async;

        new ManagerRetrofit<MessageDTO>().sendMultiPartRequest(messageDTO, new Object() {// Change (type sending) / (data sending)
        }.getClass().getEnclosingMethod().getName(), generateMultiPartList(listUri, context), new ManagerRetrofit.AsyncAnswerString() {
            @Override
            public void processFinish(Boolean isSuccess, String answerString) {
                if (isSuccess) {
                    Type typeAnswer = new TypeToken<ServerResponse>() { //Change type response(тип ответа)
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
    public void addFeedback(MessageDTO messageDTO, AnswerServerResponse<T> async) { //Change name request / Change data in method parameters
        answerServerResponse = async;
        new ManagerRetrofit<MessageDTO>().sendRequest(messageDTO, new Object() {// Change type Object sending / Change data sending
        }.getClass().getEnclosingMethod().getName(), (isSuccess, answerString) -> {
            if (isSuccess) {
                Type typeAnswer = new TypeToken<ServerResponse>() { //Change type response
                }.getType();
                tryParsing(answerString, typeAnswer);
            } else {
                answerServerResponse.processFinish(false, null);
            }
        });
    }

    /**
     * Registration DTO
     */
    public void feedbackList(GetEstimationsDTO getEstimationsDTO, AnswerServerResponse<T> async) { //Change name request / Change data in method parameters
        answerServerResponse = async;
        new ManagerRetrofit<GetEstimationsDTO>().sendRequest(getEstimationsDTO, new Object() {// Change type Object sending / Change data sending
        }.getClass().getEnclosingMethod().getName(), (isSuccess, answerString) -> {
            if (isSuccess) {
                Type typeAnswer = new TypeToken<ServerResponse<ArrayList<FeedbackDTO>>>() { //Change type response
                }.getType();
                tryParsing(answerString, typeAnswer);
            } else {
                answerServerResponse.processFinish(false, null);
            }
        });
    }

    /**
     * Registration DTO
     */
    public void updateUser(UserDTO userDTO, AnswerServerResponse<T> async) { //Change name request / Change data in method parameters
        answerServerResponse = async;
        new ManagerRetrofit<UserDTO>().sendRequest(userDTO, new Object() {// Change type Object sending / Change data sending
        }.getClass().getEnclosingMethod().getName(), (isSuccess, answerString) -> {
            if (isSuccess) {
                Type typeAnswer = new TypeToken<ServerResponse<ArrayList<FeedbackDTO>>>() { //Change type response
                }.getType();
                tryParsing(answerString, typeAnswer);
            } else {
                answerServerResponse.processFinish(false, null);
            }
        });
    }

    /**
     * //////////// Dop methods
     */

    public ArrayList<MultipartBody.Part> generateMultiPartList(ArrayList<Uri> listFileUri, Context context) {
        ArrayList<MultipartBody.Part> arrayListMulti = new ArrayList<>();
        for (int i = 0; i < listFileUri.size(); i++) {
            try {
                File file = new File(PathUtil.getPath(context, listFileUri.get(i)));
                arrayListMulti.add(MultipartBody.Part.createFormData("images[" + i + "]", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file)));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

        }
        return arrayListMulti;
    }

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
