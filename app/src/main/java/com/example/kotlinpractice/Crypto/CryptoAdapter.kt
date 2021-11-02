package com.example.kotlinpractice.Crypto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpractice.R
import kotlinx.android.synthetic.main.crypto_item_layout.view.*

class CryptoAdapter(val cryptoList: ArrayList<CryptoDataClass>, val itemClickListener: OnItemClickListener): RecyclerView.Adapter<CryptoAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.crypto_item_layout,parent, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(cryptoList.get(position))
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bindItems(crypto: CryptoDataClass) {
            itemView.tv_crypto_symbol.text = crypto.symbol
            itemView.tv_crypto_full_name.text = crypto.name
            itemView.tv_crypto_id.text = crypto.id

            itemView.button_details.setOnClickListener {
                itemClickListener.onItemClick(crypto.id)
            }
        }
    }




    interface OnItemClickListener{
        fun onItemClick(id: String)
    }



}