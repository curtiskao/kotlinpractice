package com.example.kotlinpractice.Mood

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlinpractice.Note.NoteViewModel
import com.example.kotlinpractice.R
import org.w3c.dom.Text
import kotlin.random.Random

class MoodFragment: Fragment() {

    private var brandsArray: MutableList<String> = ArrayList()
    private var foodArray: MutableList<String> = ArrayList()
    private lateinit var tv_food: TextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mood, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button_home).setOnClickListener {
            findNavController().navigate(R.id.action_MoodFragment_toHomeFragment)
        }
        view.findViewById<Button>(R.id.button_refresh).setOnClickListener{
            refresh()
        }
        tv_food = view.findViewById(R.id.tv_food)

        loadFood()
    }


    private fun loadData(){
        brandsArray.add("Nike")
        brandsArray.add("Vetements")
        brandsArray.add("Ambush")
        brandsArray.add("CDG")
        brandsArray.add("Supreme")
        brandsArray.add("Palace")
        brandsArray.add("Needles")


    }

    private fun loadFood(){
        foodArray.add("Sushi")
        foodArray.add("FastFood")
        foodArray.add("Pho")
        foodArray.add("Pasta")
        foodArray.add("Acai Bowl")
        foodArray.add("Indian")
        foodArray.add("Tacos")
        foodArray.add("Taiwanese")
        foodArray.add("American")
        foodArray.add("Hotpot")
        foodArray.add("Salad")
        foodArray.add("Dim sum")
        foodArray.add("Steak")
        foodArray.add("Fruits")
        foodArray.add("Tofu Soup")
        foodArray.add("Korean BBQ")
        foodArray.add("Yakitori")
        foodArray.add("Seafood")
        foodArray.add("Curry")
        foodArray.add("Donburi")
        foodArray.add("Korean")
        foodArray.add("Mediterranean")
        foodArray.add("Japanese")

        genRandomFood()
    }

    private fun genRandomFood(){
        val size = foodArray.size
        val randomNum = Random.nextInt(0,size)
        tv_food.text = foodArray.get(randomNum)
    }

    private fun refresh(){
        genRandomFood()
    }


}