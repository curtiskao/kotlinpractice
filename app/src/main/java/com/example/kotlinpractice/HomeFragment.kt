package com.example.kotlinpractice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import java.text.SimpleDateFormat
import java.time.Clock
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HomeFragment : Fragment() {

    private lateinit var tv_date: TextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpDate(view)
        view.findViewById<Button>(R.id.button_note).setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_NoteFragment)
        }


    }

    fun setUpDate(view: View){
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val currentDate = sdf.format(Date())
        tv_date = view.findViewById(R.id.text_date)
        tv_date.setText(currentDate)
    }
}