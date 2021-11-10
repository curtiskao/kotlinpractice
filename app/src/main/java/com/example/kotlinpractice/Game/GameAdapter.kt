package com.example.kotlinpractice.Game

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpractice.R

class GameAdapter(private val context: Context, private val numPieces: Int, private val cardImages: List<GameCard>, private val listener: CardClickListener): RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    interface CardClickListener{
        fun onCardClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.game_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = numPieces

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val imageButton: ImageButton = itemView.findViewById(R.id.button_tile)
        fun bind(position: Int){
            imageButton.setImageResource(if(cardImages.get(position).isFaceUp) cardImages[position].id else R.drawable.game_card_background)
            imageButton.setOnClickListener{
                //cardImages[position].isFaceUp = if(cardImages[position].isFaceUp) false else true
                //imageButton.setImageResource(if(cardImages.get(position).isFaceUp) cardImages[position].id else R.drawable.game_card_background)
                listener.onCardClick(position)
            }

            if(cardImages.get(position).isMatched){
                itemView.visibility = View.INVISIBLE
            }else{
                itemView.visibility = View.VISIBLE

            }
        }

    }


}