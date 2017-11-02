package kr.sofac.handsometalk.view;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;

import kr.sofac.handsometalk.R;
import kr.sofac.handsometalk.view.fragments.CalendarFragment;
import kr.sofac.handsometalk.view.fragments.ContactsFragment;
import kr.sofac.handsometalk.view.fragments.EventFragment;
import kr.sofac.handsometalk.view.fragments.InfoFragment;
import kr.sofac.handsometalk.view.fragments.PushFragment;
import kr.sofac.handsometalk.view.fragments.SettingsFragment;
import kr.sofac.handsometalk.view.fragments.TalkFragment;


public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    CalendarFragment calendarFragment;
    ContactsFragment contactsFragment;
    EventFragment eventFragment;
    InfoFragment infoFragment;
    PushFragment pushFragment;
    SettingsFragment settingsFragment;
    TalkFragment talkFragment;

    FragmentTransaction fTrans;
    CheckBox chbStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        contactsFragment = new ContactsFragment();
        calendarFragment = new CalendarFragment();
        eventFragment = new EventFragment();
        infoFragment = new InfoFragment();
        pushFragment = new PushFragment();
        settingsFragment = new SettingsFragment();
        talkFragment = new TalkFragment();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        fTrans = getFragmentManager().beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            fTrans.replace(R.id.containerFrame, calendarFragment);
        } else if (id == R.id.nav_gallery) {
            fTrans.replace(R.id.containerFrame, contactsFragment);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

//        fTrans = getFragmentManager().beginTransaction();
//        switch (v.getId()) {
//            case R.id.btnAdd:
//
//                break;
//            case R.id.btnRemove:
//                fTrans.remove(frag1);
//                break;
//            case R.id.btnReplace:
//                fTrans.replace(R.id.frgmCont, frag2);
//            default:
//                break;
//        }


        fTrans.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
