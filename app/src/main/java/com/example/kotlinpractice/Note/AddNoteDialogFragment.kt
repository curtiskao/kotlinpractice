package com.example.kotlinpractice

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

class AddNoteDialogFragment(private var dialogOnTouchListener: AddNoteDialogListener) : DialogFragment() {
    lateinit var editText: EditText

    interface AddNoteDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment, message: String)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            val dialogView: View = inflater.inflate(R.layout.add_note_dialog,null)
            editText = dialogView.findViewById(R.id.editText_note)
            builder.setView(dialogView)
            builder.setPositiveButton("Add",
                            DialogInterface.OnClickListener { dialog, id -> dialogOnTouchListener.onDialogPositiveClick(this, getMessage())
                            })
                    .setNegativeButton("Cancel",
                            DialogInterface.OnClickListener { dialog, id -> dialogOnTouchListener.onDialogNegativeClick(this)
                                // User cancelled the dialog
                            })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }

    fun getMessage():String{
        return editText.text.toString()
    }


}