package kr.sofac.handsometalk.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import kr.sofac.handsometalk.R;

public class DetailEstimateActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_estimate);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("SukaTalk");

//        new Connection<String>().newMessage(
//                getActivity(),
//                new MessageDTO("","50","1","","one more message Fuck","","","","",""),
//                listPhoto,
//                new Connection.AnswerServerResponse<String>() {
//                    @Override
//                    public void processFinish(Boolean isSuccess, ServerResponse<String> answerServerResponse) {
//                        if (isSuccess) {
//
//                        } else {
//
//                        }
//                        processBar.dismissView();
//                    }
//                }
//        );
    }
}
