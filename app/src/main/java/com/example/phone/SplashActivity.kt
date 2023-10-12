package com.example.phone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceActivity.Header
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {
    val auth  =FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            if (auth.currentUser?.uid!=null){
                val intent = Intent(this,HomeActivity2::class.java)
                startActivity(intent)
            }
            else{
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        },3000)
    }
}