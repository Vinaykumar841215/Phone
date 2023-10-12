package com.example.phone

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(val context: Context, val userList:List<DataClass>):BaseAdapter() {

    override fun getCount(): Int {
        return userList.size
     }
    override fun getItem(p0: Int): Any {
        return userList[p0]
    }
    override fun getItemId(p0: Int): Long {

        return p0.toLong()
     }
    @SuppressLint("MissingInflatedId", "ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val itemData  = userList[p0]

        val itemLayout = LayoutInflater.from(context).inflate(R.layout.item_list,p2,false)

        val name_list =itemLayout.findViewById<TextView>(R.id.named)
        val email_list = itemLayout.findViewById<TextView>(R.id.emaild)
        val age_list = itemLayout.findViewById<TextView>(R.id.aged)
        val address_list = itemLayout.findViewById<TextView>(R.id.addressd)
        val contacte_list = itemLayout.findViewById<TextView>(R.id.contacted)

        name_list.text=itemData.name
        email_list.text=itemData.email
        age_list.text=itemData.age.toString()
        address_list.text=itemData.address
        contacte_list.text=itemData.contact.toString()

        return itemLayout
    }
 }