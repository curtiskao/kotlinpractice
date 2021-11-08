package com.example.kotlinpractice.Mood

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kotlinpractice.R
import kotlin.random.Random

class MoodFragment: Fragment() {

    private var brandsArray: MutableList<String> = ArrayList()
    private var foodArray: MutableList<String> = ArrayList()
    private var actArray: MutableList<String> = ArrayList()

    private lateinit var tv_food: TextView
    private lateinit var tv_activity: TextView
    private lateinit var tv_num: TextView
    private lateinit var tv_color: TextView

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
        tv_activity = view.findViewById(R.id.tv_activity)
        tv_num = view.findViewById(R.id.tv_number)
        tv_color = view.findViewById(R.id.tv_color)

        loadFood()
        loadActivites()
        genRandomNum()
        genRandomColor()
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

    private fun loadActivites(){
        actArray.add("Stay in")
        actArray.add("Shopping")
        actArray.add("Work out")
        actArray.add("Hang out with friends")
        actArray.add("Read")
        actArray.add("FaceTime my baby")
        actArray.add("Play video games")
        actArray.add("Watch Youtube")
        actArray.add("Watch Netflix")
        actArray.add("Play basketball")
        actArray.add("Practice golf")
        actArray.add("Practice coding")
        actArray.add("Study")
        actArray.add("Eat")

        genRandomActivity()
    }

    private fun genRandomFood(){
        val size = foodArray.size
        val randomNum = Random.nextInt(0, size)
        tv_food.text = foodArray.get(randomNum)
    }

    private fun genRandomActivity(){
        val size = actArray.size
        val randomNum = Random.nextInt(0, size)
        tv_activity.text = actArray.get(randomNum)
    }

    private fun genRandomNum(){
        tv_num.text = (Random.nextInt(0, 100)+1).toString()
    }

    private fun genRandomColor(){
        val color: Int = Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
        tv_color.setTextColor(color)
    }

    private fun refresh(){
        genRandomFood()
        genRandomActivity()
        genRandomNum()
        genRandomColor()
    }


}