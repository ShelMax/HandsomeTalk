package kr.sofac.handsometalk.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import kr.sofac.handsometalk.R;
import kr.sofac.handsometalk.dto.AuthorizationDTO;
import kr.sofac.handsometalk.dto.RegistrationDTO;
import kr.sofac.handsometalk.dto.UserDTO;
import kr.sofac.handsometalk.server.Connection;
import kr.sofac.handsometalk.util.PreferenceApp;

public class AuthorizationActivity extends BaseActivity implements View.OnClickListener {

    private SessionCallback callback;
    String googleKey;
    EditText editEmail, editPassword;
    Button buttonSignIn, buttonKakaoTalk;
    TextView textRegisterLink;
    LoginButton loginButton;
    Activity localActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        editEmail = findViewById(R.id.id_edit_name);
        editPassword = findViewById(R.id.id_edit_password);

        buttonSignIn = findViewById(R.id.id_sign_in_button);
        buttonKakaoTalk = findViewById(R.id.id_kakaotalk_button);
        textRegisterLink = findViewById(R.id.id_register_text_view);
        loginButton = findViewById(R.id.com_kakao_login);
        googleKey = new PreferenceApp(this).getGoogleKey();
        localActivity = this;

        buttonSignIn.setOnClickListener(this);
        buttonKakaoTalk.setOnClickListener(this);
        textRegisterLink.setOnClickListener(this);

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        startActivity(new Intent(this, MainCustomActivity.class));
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_sign_in_button:
                if (!editEmail.getText().toString().isEmpty() && !editPassword.getText().toString().isEmpty()) {
                    progressBar.showView();
                    new Connection<UserDTO>().authorizationUser(
                            new AuthorizationDTO(
                                    editPassword.getText().toString(),
                                    editEmail.getText().toString(),
                                    googleKey),
                            (isSuccess, answerServerResponse) -> {
                                if (isSuccess) {
                                    saveUser(answerServerResponse.getDataTransferObject());

                                } else {
                                    showToastError();
                                }
                                progressBar.dismissView();
                            });
                } else {
                    Toast.makeText(this, "Fields is empty", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.id_kakaotalk_button:
                Toast.makeText(this, "TALK", Toast.LENGTH_SHORT).show();
                loginButton.performClick();
                break;
            case R.id.id_register_text_view:
                startActivity(new Intent(this, RegistrationActivity.class));
                break;
        }
    }

    public void saveUser(UserDTO userDTO) {
        PreferenceApp preferenceApp = new PreferenceApp(this);
        preferenceApp.setUser(userDTO);
        preferenceApp.setAuthorization(true);
        startMainActivity();
    }

    public void showToastError() {
        Toast.makeText(this, "Connection error!", Toast.LENGTH_SHORT).show();
    }

    public void startMainActivity() {
        startActivity(new Intent(this, MainCustomActivity.class));
        finishAffinity();
    }


    /**
     * Save login & pass
     */

    @Override
    public void onPause() {
        super.onPause();
        savePreferences();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPreferences();
    }

    private void savePreferences() {
        SharedPreferences settings = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("Username", editEmail.getText().toString());
        editor.putString("Password", editPassword.getText().toString());
        editor.apply();
        editor.commit();
    }

    private void loadPreferences() {
        SharedPreferences settings = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        editEmail.setText(settings.getString("Username", ""));
        editPassword.setText(settings.getString("Password", ""));
    }




  /** KakaoTalk API*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {

            Session.getCurrentSession().checkAndImplicitOpen();
            return;
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            UserManagement.requestMe(new MeResponseCallback() {

                @Override
                public void onFailure(ErrorResult errorResult) {
                    String message = "failed to get user info. msg=" + errorResult;
                    Logger.d(message);
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                }

                @Override
                public void onNotSignedUp() {
                }

                @Override
                public void onSuccess(UserProfile userProfile) {
                    Long id = userProfile.getId();
                    progressBar.showView();
                    new Connection<UserDTO>().authorizationUser(new AuthorizationDTO(id.toString(), googleKey), (isSuccess, answerServerResponse) -> {
                        if(isSuccess){
                            saveUser(answerServerResponse.getDataTransferObject());
                        } else {

                            Dialog dialog;
                            AlertDialog.Builder builder = new AlertDialog.Builder(localActivity);
                            LayoutInflater inflater = localActivity.getLayoutInflater();
                            View view = inflater.inflate(R.layout.dialog_enter_information, null, false);

                            EditText emailEdit = view.findViewById(R.id.email_info);
                            EditText phoneEdit = view.findViewById(R.id.phone_nubmer_info);
                            EditText nameEdit = view.findViewById(R.id.name_info);
                            Button buttonRegistration = view.findViewById(R.id.finish_registation);

                            buttonRegistration.setOnClickListener(view1 -> {
                                if(!emailEdit.getText().toString().isEmpty()&&!phoneEdit.getText().toString().isEmpty()&&!nameEdit.getText().toString().isEmpty()){
                                    new Connection<UserDTO>().registrationUser(new RegistrationDTO(id.toString(),nameEdit.getText().toString(),phoneEdit.getText().toString(),emailEdit.getText().toString(), googleKey), (isSuccess1, answerServerResponse1) -> {
                                        if(isSuccess1){
                                            saveUser(answerServerResponse1.getDataTransferObject());
                                        } else {
                                            showToastError();
                                        }
                                    });
                                }else {
                                    Toast.makeText(localActivity, "Field empty!", Toast.LENGTH_SHORT).show();
                                }
                            });

                            builder.setView(view);
                            dialog = builder.create();
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dialog.setCancelable(false);
                            dialog.show();
                        }
                        progressBar.dismissView();
                    });
                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
        }
    }

}
