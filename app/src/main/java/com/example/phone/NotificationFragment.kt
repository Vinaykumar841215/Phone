package com.example.phone

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

class NotificationFragment : Fragment() {
    val auth = FirebaseAuth.getInstance()
 private val  db = FirebaseFirestore.getInstance()
    lateinit var recyclerView: RecyclerView
    var userList = ArrayList<DataClass>()
    @SuppressLint("SuspiciousIndentation", "MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      val view= inflater.inflate(R.layout.fragment_notification, container, false)

         recyclerView=view.findViewById(R.id.recyclerview)
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        userList= arrayListOf()
        db.collection("user").get()
            .addOnSuccessListener {
                if (!it.isEmpty){
                 for (data in it.documents){
                     val NewAdapter:DataClass?=data.toObject(DataClass::class.java)
                     if (NewAdapter!=null){
                         userList.add(NewAdapter)

                     }
                 }
                    recyclerView.adapter=NewAdapter(userList)
                }
            }

        return view
    }
}