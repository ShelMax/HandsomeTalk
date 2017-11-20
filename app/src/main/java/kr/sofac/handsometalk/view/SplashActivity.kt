package kr.sofac.handsometalk.view
import android.content.Intent
import android.os.Bundle
import kr.sofac.handsometalk.R

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startActivity(Intent(this, MainCustomActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }

}
