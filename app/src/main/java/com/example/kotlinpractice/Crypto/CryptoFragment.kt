package com.example.kotlinpractice.Crypto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpractice.R


class CryptoFragment: Fragment() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crypto, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button_home).setOnClickListener {
            findNavController().navigate(R.id.action_CryptoFragment_toHomeFragment)
        }
        recyclerView = view.findViewById(R.id.recyclerview_crypto)
        setAdapter()
    }

    fun setAdapter(){
        val c1 = CryptoDataClass("one","1.0")
        val c2 = CryptoDataClass("two", "2.0")
        val list = ArrayList<CryptoDataClass>()
        list.add(c1)
        list.add(c2)
        val cryptoAdapter = CryptoAdapter(list)

        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = cryptoAdapter
    }

}