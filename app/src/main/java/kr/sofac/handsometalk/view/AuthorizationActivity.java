package kr.sofac.handsometalk.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import kr.sofac.handsometalk.R;

public class AuthorizationActivity extends AppCompatActivity {

    EditText editEmail, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        editEmail = findViewById(R.id.);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        startActivity(new Intent(this, MainCustomActivity.class));
        super.onBackPressed();
    }
}
