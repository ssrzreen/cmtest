package com.example.cmapplication.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cmapplication.Detail
import com.example.cmapplication.R
import com.example.cmapplication.model.MobileDetails
import com.squareup.picasso.Picasso

class MobileDetailsAdapter(
    private val mobileDetailsList: List<MobileDetails>,
    private val context: Context
) : RecyclerView.Adapter<MobileDetailsAdapter.MobileDetailHolder>() {

    private lateinit var mListener : onItemClickListener
//    public void setSearchList(List<DataClass> dataSearchList){
//        this.mobileDetailsList = dataSearchList;
//        notifyDataSetChanged();
//    }
    interface onItemClickListener {
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener : onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobileDetailHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.activity_mobile_ui, parent, false)
        return MobileDetailHolder(itemView , mListener)
    }

    override fun onBindViewHolder(holder: MobileDetailHolder, position: Int) {

        holder.mobileTitle.text = mobileDetailsList[position].title
        holder.mobilePrice.text = mobileDetailsList[position].price
        holder.mobileStock.text = mobileDetailsList[position].stock
//        holder.mobileId.text = mobileDetailsList[position].id
        Picasso.get().load(mobileDetailsList[position].pic).into(holder.mobilePic)

//        holder.mobileBtnView.setOnClickListener(new View.OnclickListener() {
//            public void onClick(view view){
//                Intent intent = new Intent(context,Detail.class);
//                intent.putExtra("image" , mobileDetailsList.get(holder.adapterPosition))
//            }
//        })
    }

    override fun getItemCount(): Int {
        return mobileDetailsList.size
    }

    inner class MobileDetailHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val mobileTitle: TextView = itemView.findViewById(R.id.title)
        val mobilePic: ImageView = itemView.findViewById(R.id.image)
        val mobilePrice: TextView = itemView.findViewById(R.id.price)
        val mobileStock: TextView = itemView.findViewById(R.id.stock)
//        val mobileId : TextView = itemView.findViewById(R.id.id)
//        val mobileBtnView : Button = itemView.findViewById(R.id.viewbtn)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

       
    }
}

