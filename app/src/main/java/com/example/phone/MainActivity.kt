package com.example.phone

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    var verificationId = " "
    private var auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val phone = findViewById<EditText>(R.id.phone)
        val verify = findViewById<EditText>(R.id .verify_otp)
        val sendOtp = findViewById<Button>(R.id.send_otp)
        val verifyBtn = findViewById<Button>(R.id.verify_button)


        sendOtp.setOnClickListener {
            sendOtops(phone.text.toString())

        }
        verifyBtn.setOnClickListener {
            val crossinline =
                PhoneAuthProvider.getCredential(verificationId, verify.text.toString())
            verifyOtps(crossinline)
        }


        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                verifyOtps(p0)
            }

            override fun onVerificationFailed(p0: FirebaseException) {

            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                verificationId=p0
            }
        }
    }

    fun sendOtops(phone: String) {
        val phoneAuthOptions = PhoneAuthOptions.newBuilder()
            .setPhoneNumber("+91$phone")
            .setTimeout(90L, TimeUnit.SECONDS)
            .setCallbacks(callbacks)
            .setActivity(this)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)
    }

    fun verifyOtps(crossinline: PhoneAuthCredential) {
        auth.signInWithCredential(crossinline)
            .addOnSuccessListener {

                checkuser()

                Toast.makeText(this, "Verify OTP", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this@MainActivity, "InCorrect OTP", Toast.LENGTH_SHORT).show()
            }
    }

    fun checkuser (){
        val userId = auth.currentUser?.uid
        db.collection("user").whereEqualTo("userId",userId).get()
            .addOnSuccessListener {
                if (it.isEmpty){
                    val intent = Intent(this, RegistationActivity::class.java)
                    startActivity(intent)
                }
                else{
                    val intent = Intent(this, HomeActivity2::class.java)
                    startActivity(intent)
                }
//                val intent = Intent(this, NotificationFragment::class.java)
//                startActivity(intent)
            }
    }
}