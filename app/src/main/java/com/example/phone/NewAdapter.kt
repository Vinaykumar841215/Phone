package com.example.phone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class NewAdapter(private val userList:ArrayList<DataClass>):RecyclerView.Adapter<NewAdapter.MyViewHolder>() {
    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val namer:TextView=itemView.findViewById(R.id.named)
        val emailr:TextView=itemView.findViewById(R.id.emaild)
        val ager :TextView=itemView.findViewById(R.id.aged)
        val addressr:TextView=itemView.findViewById(R.id.addressd)
        val contactr:TextView=itemView.findViewById(R.id.contacted)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.new_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.namer.text=userList[position].name
        holder.emailr.text=userList[position].email
        holder.ager.text=userList[position].age.toString()
        holder.addressr.text=userList[position].address
        holder.contactr.text=userList[position].contact.toString()
    }


}