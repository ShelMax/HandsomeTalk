package kr.sofac.handsometalk.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.usermgmt.LoginButton;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import kr.sofac.handsometalk.R;
import kr.sofac.handsometalk.dto.AuthorizationDTO;
import kr.sofac.handsometalk.dto.UserDTO;
import kr.sofac.handsometalk.server.Connection;
import kr.sofac.handsometalk.util.PreferenceApp;
import timber.log.Timber;

public class AuthorizationActivity extends BaseActivity implements View.OnClickListener {

    private SessionCallback callback;

    EditText editEmail, editPassword;
    Button buttonSignIn, buttonKakaoTalk;
    TextView textRegisterLink;
    LoginButton loginButton;

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

        buttonSignIn.setOnClickListener(this);
        buttonKakaoTalk.setOnClickListener(this);
        textRegisterLink.setOnClickListener(this);

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();
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
                                    new PreferenceApp(this).getGoogleKey()),
                            (isSuccess, answerServerResponse) -> {
                                if (isSuccess) {
                                    saveUser(answerServerResponse.getDataTransferObject());
                                    startMainActivity();
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
            Timber.e("Kakao data : "+data.toString());
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
            startMainActivity();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
        }
    }

}
