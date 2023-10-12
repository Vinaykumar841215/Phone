package com.example.phone

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

   private val auth = FirebaseAuth.getInstance()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
         val userId =auth.currentUser?.uid.toString()


        val editname = view.findViewById<EditText>(R.id.NameEdit)
        val editage = view.findViewById<EditText>(R.id.Ageedit)
        val editemail = view.findViewById<EditText>(R.id.Emailedit)
        val editaddress = view.findViewById<EditText>(R.id.Addressedit)
        val editcontact = view.findViewById<EditText>(R.id.Phoneedit)
        val btn = view.findViewById<AppCompatButton>(R.id.editbtn)
        val db = Firebase.firestore

        val res = db.collection("user").document(userId)
        res.get().addOnSuccessListener {
            if (it != null) {

                val name1 = it.data?.get("name")?.toString()
                val age1 = it.data?.get("age")?.toString()
                val email1 = it.data?.get("email")?.toString()
                val address1 = it.data?.get("address")?.toString()
                val phone1 = it.data?.get("contact")?.toString()

                editname?.setText(name1)
                editage?.setText(age1)
                editemail?.setText(email1)
                editaddress?.setText(address1)
                editcontact?.setText(phone1)
            }
        }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()

            }
        btn.setOnClickListener {
            val dataClass=DataClass(editname.text.toString(),editemail.text.toString()
                ,editage.text.toString().toLong(),editaddress.text.toString(),editcontact.text.toString().toLong(),userId)
            db.collection("user").document(userId).set(dataClass)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "uptade success", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(requireContext(),HomeActivity2::class.java))
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

        }

        return view
    }
}