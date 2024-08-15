package com.example.cmapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cmapplication.R
import com.example.cmapplication.model.MobileDetails
import com.squareup.picasso.Picasso

class MobileDetailsAdapter(
    private val mobileDetailsList: List<MobileDetails>,
    private val context: Context
) : RecyclerView.Adapter<MobileDetailsAdapter.MobileDetailHolder>() {

    private lateinit var mListener : onItemClickListener
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
        Picasso.get().load(mobileDetailsList[position].pic).into(holder.mobilePic)
    }
    override fun getItemCount(): Int {
        return mobileDetailsList.size
    }

    inner class MobileDetailHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val mobileTitle: TextView = itemView.findViewById(R.id.title)
        val mobilePic: ImageView = itemView.findViewById(R.id.image)
        val mobilePrice: TextView = itemView.findViewById(R.id.price)
        val mobileStock: TextView = itemView.findViewById(R.id.stock)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

       
    }
}

