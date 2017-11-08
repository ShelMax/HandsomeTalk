package kr.sofac.handsometalk.view;

import android.content.Intent;
import android.os.Bundle;

import kr.sofac.handsometalk.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startActivity();
    }

    public void startActivity(){
        Intent intent = new Intent(this, MainCustomActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
