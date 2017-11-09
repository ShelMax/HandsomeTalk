package kr.sofac.handsometalk.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kr.sofac.handsometalk.R;
import kr.sofac.handsometalk.dto.RegistrationDTO;
import kr.sofac.handsometalk.dto.UserDTO;
import kr.sofac.handsometalk.server.Connection;
import kr.sofac.handsometalk.server.type.ServerResponse;
import kr.sofac.handsometalk.util.PreferenceApp;

public class RegistrationActivity extends BaseActivity implements View.OnClickListener {

    EditText editName, editEmail, editPhoneNumber, editPassword, getEditPasswordConfirm;
    Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editName = findViewById(R.id.id_edit_name);
        editEmail = findViewById(R.id.id_edit_email);
        editPhoneNumber = findViewById(R.id.id_edit_phone);
        editPassword = findViewById(R.id.id_password);
        getEditPasswordConfirm = findViewById(R.id.id_confirm_pasword);
        buttonSignUp = findViewById(R.id.id_sign_up_button);
        buttonSignUp.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_sign_up_button:
                String name = editName.getText().toString();
                String email = editEmail.getText().toString();
                String phoneNumber = editPhoneNumber.getText().toString();
                String password = editPassword.getText().toString();
                String confirmPassword = getEditPasswordConfirm.getText().toString();
                if (!name.isEmpty()&&!email.isEmpty()&&!phoneNumber.isEmpty()&&!password.isEmpty()&&!confirmPassword.isEmpty()&&(password.equals(confirmPassword))) {
                    progressBar.showView();
                    new Connection<UserDTO>().registrationUser(new RegistrationDTO(
                                    password,
                                    name,
                                    true,
                                    phoneNumber,
                                    email,
                                    new PreferenceApp(this).getGoogleKey()),
                            new Connection.AnswerServerResponse<UserDTO>() {
                                @Override
                                public void processFinish(Boolean isSuccess, ServerResponse<UserDTO> answerServerResponse) {
                                    if (isSuccess) {
                                        saveUser(answerServerResponse.getDataTransferObject());
                                        startMainActivity();
                                    } else {
                                        showToastError();
                                    }
                                    progressBar.dismissView();
                                }
                            });
                } else {
                    Toast.makeText(this, "Field is empty!", Toast.LENGTH_SHORT).show();
                }
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
}
