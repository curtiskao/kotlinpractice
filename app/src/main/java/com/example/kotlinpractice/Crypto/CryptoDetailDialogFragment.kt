package com.example.kotlinpractice.Crypto

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.kotlinpractice.Note.AddNoteDialogFragment
import com.example.kotlinpractice.R

class CryptoDetailDialogFragment(val cryptoObject: CryptoDataClass) : DialogFragment() {
    lateinit var textView: TextView


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            val dialogView: View = inflater.inflate(R.layout.crypto_detail_dialog, null)
            textView = dialogView.findViewById(R.id.tv_details)
            textView.text = cryptoObject.id
            builder.setView(dialogView)
            builder.setNegativeButton("Ok",
                            DialogInterface.OnClickListener { dialog, id ->
                                dismiss()
                                // User cancelled the dialog
                            })

            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }



}