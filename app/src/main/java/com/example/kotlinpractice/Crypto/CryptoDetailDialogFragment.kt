package com.example.kotlinpractice.Crypto

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.kotlinpractice.R

class CryptoDetailDialogFragment(val cryptoObject: CryptoDataClass) : DialogFragment() {
    lateinit var tv_id: TextView


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            val dialogView: View = inflater.inflate(R.layout.crypto_detail_dialog, null)
            setValues(dialogView)

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

    private fun setValues(view: View){
        tv_id = view.findViewById(R.id.tv_id)
        tv_id.text = cryptoObject.id

        val tv_name = view.findViewById<TextView>(R.id.tv_name)
        tv_name.text = cryptoObject.name

        val tv_isActive = view.findViewById<TextView>(R.id.tv_is_active)
        tv_isActive.text = if(cryptoObject.isActive) "Active" else "Inactive"

        val tv_rank = view.findViewById<TextView>(R.id.tv_rank)
        tv_rank.text = "Rank: " +cryptoObject.rank

        val tv_desc = view.findViewById<TextView>(R.id.tv_description)
        tv_desc.text = cryptoObject.description
    }



}