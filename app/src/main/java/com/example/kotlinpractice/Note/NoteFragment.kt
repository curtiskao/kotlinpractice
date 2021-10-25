package com.example.kotlinpractice.Note

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlinpractice.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NoteFragment : Fragment(), AddNoteDialogFragment.AddNoteDialogListener {

    private lateinit var currentNoteItem: NoteItem;
    private lateinit var notesLayout: LinearLayout
    private var noteList = mutableListOf<String>()
    private lateinit var noteViewModel: NoteViewModel

    companion object{
        const val MAX_NUM_OF_NOTES = 7
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            updateNoteList()
        }
        notesLayout = view.findViewById(R.id.linearLayout_notes)


        //viewmodel
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.allWords.observe(viewLifecycleOwner, Observer {
            words -> words.let { setWords(it as List<Note>) }
        })

        setListeners(view)
    }

    fun setListeners(view: View){
        view.findViewById<Button>(R.id.button_add).setOnClickListener{
            if(notesLayout.childCount< MAX_NUM_OF_NOTES +1){
                showAddNoteDialog()
            }
        }
        view.findViewById<Button>(R.id.button_deleteAll).setOnClickListener{
            notesLayout.removeAllViews()
            noteViewModel.deleteAll()
        }

        view.findViewById<Button>(R.id.button_db).setOnClickListener{
            noteViewModel.deleteAll()
        }
    }

    fun setWords(list: List<Note>){
        Log.d("item size",": " +list.size)
        notesLayout.removeAllViews()
        list.forEach{
            addNoteItem(it.note)
        }
    }

    fun showAddNoteDialog(){
        val listener: AddNoteDialogFragment.AddNoteDialogListener = this as AddNoteDialogFragment.AddNoteDialogListener
        val addNoteDialog = AddNoteDialogFragment(listener)
        addNoteDialog.show(parentFragmentManager,"AddNoteDialog")
    }

    fun addNoteItem(text: String = "next item"){
        val noteItem: NoteItem? = context?.let { NoteItem(it) };
        if (noteItem != null) {
            notesLayout.addView(noteItem)
            noteItem.setText(text)
            noteItem.getDeleteButton().setOnClickListener{removeView(noteItem,text)}
            val newNote = Note(text)
            noteViewModel.insert(newNote)
        }

    }

    fun removeView(view: View, text: String){
        if(notesLayout.childCount > 0){
            noteViewModel.delete(text)
            notesLayout.removeView(view)
        }
    }

    fun updateNoteList(){
        noteList.clear()
        for(i in 0..notesLayout.childCount-1){
            if(notesLayout.getChildAt(i) is NoteItem){
                noteList.add((notesLayout.getChildAt(i) as NoteItem).getText())
            }
        }
        Log.d("noteList",noteList.toString())
    }

    override fun onDialogPositiveClick(dialog: DialogFragment, message: String) {
        addNoteItem(message)
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
    }

}