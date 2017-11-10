package kr.sofac.handsometalk.view;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import kr.sofac.handsometalk.Constants;
import kr.sofac.handsometalk.R;

import static kr.sofac.handsometalk.Constants.TYPE_CONTENT_NAVIGATION;

public class MainCustomActivity extends BaseActivity implements View.OnClickListener {

    ImageButton calendarButton, contactsButton, infoButton, eventButton, pushButton, talkButton, settingsButton;
    LinearLayout container;
    AnimationDrawable anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_custom);
        calendarButton = findViewById(R.id.id_calendar_button);
        contactsButton = findViewById(R.id.id_contacts_button);
        infoButton = findViewById(R.id.id_info_button);
        eventButton = findViewById(R.id.id_event_button);
        pushButton = findViewById(R.id.id_push_button);
        talkButton = findViewById(R.id.id_talk_button);
        settingsButton = findViewById(R.id.id_settings_button);

        calendarButton.setOnClickListener(this);
        contactsButton.setOnClickListener(this);
        infoButton.setOnClickListener(this);
        eventButton.setOnClickListener(this);
        pushButton.setOnClickListener(this);
        talkButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);

        container = findViewById(R.id.idLiner2);
        anim = (AnimationDrawable) container.getBackground();
        anim.setEnterFadeDuration(3000);
        anim.setExitFadeDuration(3000);
    }

    public void startChoiceActivity(String typeFragment) {
        Intent intent = new Intent(this, NavigationActivity.class);
        intent.putExtra(TYPE_CONTENT_NAVIGATION, typeFragment);
        startActivity(intent);
    }

    /**
     * Back press
     */
    private static long backPressed;

    @Override
    public void onBackPressed() {
        if (backPressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finishAffinity();
        } else {
            Toast.makeText(getBaseContext(), getString(R.string.ToastLogOut), Toast.LENGTH_SHORT).show();
        }
        backPressed = System.currentTimeMillis();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_calendar_button:
                startChoiceActivity(Constants.CALENDAR_FRAGMENT);
                break;
            case R.id.id_contacts_button:
                startChoiceActivity(Constants.CONTACTS_FRAGMENT);
                break;
            case R.id.id_info_button:
                startChoiceActivity(Constants.INFO_FRAGMENT);
                break;
            case R.id.id_event_button:
                startChoiceActivity(Constants.EVENT_FRAGMENT);
                break;
            case R.id.id_push_button:
                startChoiceActivity(Constants.PUSH_FRAGMENT);
                break;
            case R.id.id_talk_button:
                startChoiceActivity(Constants.TALK_FRAGMENT);
                break;
            case R.id.id_settings_button:
                startChoiceActivity(Constants.SETTINGS_FRAGMENT);
                break;
            case R.id.id_website_button:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.eventme.asia/")));
                break;
        }
    }



    // Starting animation:- start the animation on onResume.
    @Override
    protected void onResume() {
        super.onResume();
        if (anim != null && !anim.isRunning())
            anim.start();
    }

    // Stopping animation:- stop the animation on onPause.
    @Override
    protected void onPause() {
        super.onPause();
        if (anim != null && anim.isRunning())
            anim.stop();
    }
}
