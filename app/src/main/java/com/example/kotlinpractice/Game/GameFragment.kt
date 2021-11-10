package com.example.kotlinpractice.Game

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpractice.R

class GameFragment: Fragment() {

    private var moves = 0
    private var memoryCards: List<GameCard>? = null
    private var faceUpCards = 0
    private var correctPairs = 0
    private var card1: GameCard? = null
    private var card2: GameCard? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var tv_counter: TextView
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button_home).setOnClickListener {
            findNavController().navigate(R.id.action_GameFragment_to_HomeFragment)
        }
        view.findViewById<Button>(R.id.button_restart).setOnClickListener{
            restart()
        }
        tv_counter = view.findViewById(R.id.tv_move_counter)
        recyclerView = view.findViewById(R.id.recyclerview_game)
        setRecyclerView()

    }

    private fun setRecyclerView(){
        val images = ICONS.shuffled()
        memoryCards = images.map {
            GameCard(it)
        }
        recyclerView.adapter = context?.let { GameAdapter(it,10, memoryCards!!, object: GameAdapter.CardClickListener{
            override fun onCardClick(position: Int) {
                updateCounter()
                handleCardClick(position)
            }

        })
        }
        recyclerView.layoutManager = GridLayoutManager(context,2)

    }

    private fun updateCounter(){
        moves++
        tv_counter.text = moves.toString()
    }

    private fun handleCardClick(position: Int){
        if(!memoryCards!![position].isFaceUp){
            faceUpCards++
            if(faceUpCards == 2){
                memoryCards!![position].isFaceUp = true
                card2 = memoryCards!![position]
            }else{
                memoryCards!!.forEach {
                    it.isFaceUp = false
                }
                memoryCards!![position].isFaceUp = true
                card1 = memoryCards!![position]
                faceUpCards = 1
                card2 = null
            }

            if(card1!=null && card2!=null && card1!!.id==card2!!.id){
                clearMatchedPair(card1!!.id)
            }
        }
        recyclerView.adapter!!.notifyDataSetChanged()
    }

    private fun clearMatchedPair(id: Int){
        correctPairs++
        for(i in 0..memoryCards!!.size-1){
            if(memoryCards!![i].id == id){
                memoryCards!![i].isMatched = true
            }
        }
        card1 = null
        card2 = null
        faceUpCards = 0


        if(correctPairs==5){
            youWin()
        }
    }

    private fun youWin(){
        //show pop up and restart

        val dialogBuilder = AlertDialog.Builder(context)

        // set message of alert dialog
        dialogBuilder.setMessage("You win!")
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("Ok", DialogInterface.OnClickListener {
                    dialog, id -> restart()
            }).create().show()


    }

    private fun restart(){
        moves = -1
        faceUpCards = 0
        correctPairs = 0
        card1 = null
        card2 = null
        updateCounter()
        setRecyclerView()
    }
}