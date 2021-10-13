package com.example.kotlinpractice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.get
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var currentNoteItem: NoteItem;
    private lateinit var notesLayout: LinearLayout

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

        }
        notesLayout = view.findViewById(R.id.linearLayout_notes)

        view.findViewById<Button>(R.id.button_add).setOnClickListener{addNoteItem()}
        currentNoteItem = view.findViewById(R.id.noteItem1)
        currentNoteItem.setText("first item")
    }

    fun addNoteItem(text: String = "next item"){
        Toast.makeText(context,"add note item", Toast.LENGTH_SHORT).show()
        val noteItem: NoteItem? = context?.let { NoteItem(it) };

        notesLayout.addView(noteItem)
        if (noteItem != null) {
            noteItem.setText(text)
            noteItem.getDeleteButton().setOnClickListener{removeView(noteItem)}
        }

    }

    fun removeView(view: View){
        if(notesLayout.childCount > 1){
            notesLayout.removeView(view)
        }
    }

}