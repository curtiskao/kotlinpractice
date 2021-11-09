package com.example.kotlinpractice.Game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpractice.R

class GameFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
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
            findNavController().navigate(R.id.action_MoodFragment_toHomeFragment)
        }
        view.findViewById<Button>(R.id.button_restart).setOnClickListener{

        }
        recyclerView = view.findViewById(R.id.recyclerview_game)
        setRecyclerView()

    }

    private fun setRecyclerView(){
        val images = ICONS.shuffled()
        val memoryCards = images.map {
            GameCard(it)
        }
        recyclerView.adapter = context?.let { GameAdapter(it,8, memoryCards, object: GameAdapter.CardClickListener{
            override fun onCardClick(position: Int) {
                Log.d("clicked"," position: $position")
            }

        })
        }
        recyclerView.layoutManager = GridLayoutManager(context,2)

    }
}