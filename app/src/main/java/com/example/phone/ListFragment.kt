package com.example.phone

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class ListFragment : Fragment() {
val db = FirebaseFirestore.getInstance()
    lateinit var userAdapter:UserAdapter
    private lateinit var array: ArrayList<DataClass>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

       val view = inflater.inflate(R.layout.fragment_list, container, false)
       array= arrayListOf()
        getUserList()
        userAdapter=UserAdapter(requireContext(),array)
        val listView=view.findViewById<ListView>(R.id.list_view)
            listView.adapter=userAdapter
        return view
    }
@SuppressLint("SuspiciousIndentation")
fun getUserList(){
    db.collection("user").get()
        .addOnSuccessListener {
            Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
            val result = it.toObjects(DataClass::class.java)
                array.addAll(result)
            userAdapter.notifyDataSetChanged()

        }
        .addOnFailureListener {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        }

}

}

