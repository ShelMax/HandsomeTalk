package kr.sofac.handsometalk.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kr.sofac.handsometalk.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    fun startMainActivity() {
        val intent = Intent(this, AuthorizationActivity::class.java)
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }

    fun startRegistration() {
        val intent = Intent(this, AuthorizationActivity::class.java)
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }

}
