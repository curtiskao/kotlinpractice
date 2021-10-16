package com.example.kotlinpractice.Note

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.kotlinpractice.R

class NoteItem: LinearLayout {


    constructor(context: Context) : super(context) {
        Log.d(TAG, "CompassView(context) called")
        View.inflate(context, R.layout.note_item_layout, this)
        Log.d(TAG, "Inflation started from constructor.")
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        Log.d(TAG, "CompassView(context, attrs) called")
        View.inflate(context, R.layout.note_item_layout, this)
        Log.d(TAG, "Inflation started from constructor.")
    }

    fun setText(text: String){
        val textView: TextView = findViewById(R.id.tv_note_content);
        textView.setText(text);
    }

    fun getText(): String{
        val textView: TextView = findViewById(R.id.tv_note_content);
        return textView.text.toString()
    }

    @SuppressLint("WrongViewCast")
    fun getDeleteButton(): Button {
        return findViewById<Button>(R.id.button_delete)
    }


    companion object {
        private const val TAG = "NOTE_ITEM"
    }
}