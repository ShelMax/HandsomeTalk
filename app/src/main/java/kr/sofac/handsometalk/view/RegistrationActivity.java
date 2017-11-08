package kr.sofac.handsometalk.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import kr.sofac.handsometalk.R;
import kr.sofac.handsometalk.dto.RegistrationDTO;
import kr.sofac.handsometalk.dto.UserDTO;
import kr.sofac.handsometalk.server.Connection;
import kr.sofac.handsometalk.server.type.ServerResponse;
import kr.sofac.handsometalk.util.PreferenceApp;

public class RegistrationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        progressBar.showView();
        new Connection<UserDTO>().registrationUser(new RegistrationDTO("", "", true, "", "", new PreferenceApp(this).getGoogleKey()), new Connection.AnswerServerResponse<UserDTO>() {
            @Override
            public void processFinish(Boolean isSuccess, ServerResponse<UserDTO> answerServerResponse) {
                if(isSuccess){
                    saveUser(answerServerResponse.getDataTransferObject());
                    startMainActivity();
                }else{
                    showToastError();
                }
                progressBar.dismissView();
            }
        });
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

}
