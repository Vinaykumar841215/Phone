package com.example.phone

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {
    val auth = FirebaseAuth.getInstance()
    val data = Firebase.firestore

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_home, container, false)

//        val logout = view.findViewById<Button>(R.id.logoutBtn)
//
//        logout.setOnClickListener {
//            auth.signOut()
//
//           val intent=Intent(requireContext(),MainActivity::class.java)
//            startActivity(intent)
//
//            Toast.makeText(requireContext(), "Successfully logout", Toast.LENGTH_SHORT).show()
//        }

        val userId = Firebase.auth.currentUser?.uid.toString()
        val db = Firebase.firestore

        db.collection("user").whereEqualTo("userId", userId).get()
            .addOnSuccessListener { querySnapshot ->

                for (document in querySnapshot.documents) {
                    var userData = document.data

                    val userName = userData?.get("name")
                    val userEmail = userData?.get("email")
                    val userAge = userData?.get("age")
                    val userAddress = userData?.get("address")
                    val userContact = userData?.get("contact")

                    val getName = view.findViewById<TextView>(R.id.getName)
                    val getEmail = view.findViewById<TextView>(R.id.getEmail)
                    val getAge = view.findViewById<TextView>(R.id.getAge)
                    val getAddress = view.findViewById<TextView>(R.id.getAddress)
                    val getContact = view.findViewById<TextView>(R.id.getContact)

                    getName.text = "$userName!"
                    getEmail.text = "$userEmail"
                    getAge.text = "$userAge"
                    getAddress.text = "$userAddress"
                    getContact.text = "$userContact"
                }
                // Display a success message
                Toast.makeText(requireContext(), "Data retrieved successfully", Toast.LENGTH_SHORT)
                    .show()
            }

            .addOnFailureListener { exception ->
                // This block is executed when there's an error

                Toast.makeText(
                    requireContext(),
                    "Failed to retrieve data: $exception",
                    Toast.LENGTH_SHORT
                ).show()
                // Handle the error, e.g., log it or display an error message
            }
        val delete = view.findViewById<Button>(R.id.dataDelete)
        delete.setOnClickListener {
            if (userId != null) {
                data.collection("user").document(userId).delete()
                    .addOnSuccessListener {
                        Toast.makeText(requireContext(), "delete sucessfully", Toast.LENGTH_SHORT)
                            .show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(requireContext(), "not delete", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        val logout = view.findViewById<Button>(R.id.logoutBtn)

        logout.setOnClickListener {
            auth.signOut()

            val intent=Intent(requireContext(),MainActivity::class.java)
            startActivity(intent)

            Toast.makeText(requireContext(), "Successfully logout", Toast.LENGTH_SHORT).show()
        }
        return view
    }


}