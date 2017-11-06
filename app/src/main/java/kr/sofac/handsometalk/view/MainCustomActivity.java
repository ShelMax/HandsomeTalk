package kr.sofac.handsometalk.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import kr.sofac.handsometalk.Constants;
import kr.sofac.handsometalk.R;

import static kr.sofac.handsometalk.Constants.TYPE_CONTENT_NAVIGATION;

public class MainCustomActivity extends AppCompatActivity implements View.OnClickListener {

    Button calendarButton, contactsButton, infoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_custom);
        calendarButton = findViewById(R.id.id_calendar_button);
        contactsButton = findViewById(R.id.id_contacts_button);
        infoButton = findViewById(R.id.id_info_button);
        calendarButton.setOnClickListener(this);
        contactsButton.setOnClickListener(this);
        infoButton.setOnClickListener(this);
    }

    public void startChoiceActivity(String typeFragment){
        Intent intent = new Intent(this, NavigationActivity.class);
        intent.putExtra(TYPE_CONTENT_NAVIGATION, typeFragment);
        startActivity(intent);
    }

    private static long backPressed;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (backPressed + 2000 > System.currentTimeMillis()) {
                super.onBackPressed();
                finishAffinity();
            } else {
                Toast.makeText(getBaseContext(), getString(R.string.ToastLogOut), Toast.LENGTH_SHORT).show();
            }
            backPressed = System.currentTimeMillis();
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.id_calendar_button :
                startChoiceActivity(Constants.CALENDAR_FRAGMENT);
                break;
            case R.id.id_contacts_button :
                startChoiceActivity(Constants.CONTACTS_FRAGMENT);
                break;
            case R.id.id_info_button :
                startChoiceActivity(Constants.INFO_FRAGMENT);
                break;
        }
    }
}
