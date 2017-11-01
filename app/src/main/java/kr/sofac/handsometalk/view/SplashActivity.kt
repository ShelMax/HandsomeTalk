package kr.sofac.handsometalk.view

import android.content.Intent
import android.os.Bundle
import kr.sofac.handsometalk.R

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startAuthorization()
    }

    fun startMainActivity() {
        val intent = Intent(this, AuthorizationActivity::class.java)
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }

    private fun startAuthorization() {
        val intent = Intent(this, AuthorizationActivity::class.java)
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }

}
