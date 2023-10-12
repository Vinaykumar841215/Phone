package com.example.phone

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistationActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registation)

        auth= Firebase.auth

        var name=findViewById<EditText>(R.id.userName)
        var age = findViewById<EditText>(R.id.userAge)
        var email = findViewById<EditText>(R.id.userEmail)
        var address  =findViewById<EditText>(R.id.userAddress)
        var contact = findViewById<EditText>(R.id.userPhone)
        var register_Btn  =findViewById<Button>(R.id.register_Btn)

        register_Btn.setOnClickListener {
            var userId= Firebase.auth.currentUser?.uid.toString()
            var collection = Firebase.firestore.collection("user")

            var userDtat= DataClass(
                name =name.text.toString(),
                age = age.text.toString().toLong(),
                email = email.text.toString(),
                userId = userId,
                address = address.text.toString(),
                contact = contact.text.toString().toLong()
            )
            collection.document(userId).set(userDtat).addOnSuccessListener {
                    Toast.makeText(this, "Successfully", Toast.LENGTH_SHORT).show()
                val intent=Intent(this,HomeActivity2::class.java)
                startActivity(intent)

                }
                .addOnFailureListener {
                    Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()
                }
        }


    }
}