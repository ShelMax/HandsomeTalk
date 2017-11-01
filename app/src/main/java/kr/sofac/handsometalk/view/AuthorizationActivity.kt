package kr.sofac.handsometalk.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_authorization.*
import kr.sofac.handsometalk.R

class AuthorizationActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        idButtonLogin.setOnClickListener(this)
        idButtonKakaoTalk.setOnClickListener(this)
        idButtonRegistration.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.idButtonLogin -> {
                Toast.makeText(this, "idButtonLogin", Toast.LENGTH_SHORT).show()
            }
            R.id.idButtonKakaoTalk -> {
                Toast.makeText(this, "idButtonKakaoTalk", Toast.LENGTH_SHORT).show()
            }
            R.id.idButtonRegistration -> {
                Toast.makeText(this, "idButtonRegistration", Toast.LENGTH_SHORT).show()
            }
            else -> {
            }
        }
    }
}
