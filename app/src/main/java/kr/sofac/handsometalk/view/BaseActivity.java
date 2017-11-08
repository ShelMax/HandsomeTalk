package kr.sofac.handsometalk.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import kr.sofac.handsometalk.R;
import kr.sofac.handsometalk.util.ProgressBar;

public class BaseActivity extends AppCompatActivity {

    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        progressBar = new ProgressBar(this);
    }
}
