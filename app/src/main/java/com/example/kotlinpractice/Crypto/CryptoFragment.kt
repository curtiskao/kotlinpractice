package com.example.kotlinpractice.Crypto

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpractice.Note.NoteViewModel
import com.example.kotlinpractice.R
import dagger.hilt.android.AndroidEntryPoint

class CryptoFragment: Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var cryptoViewModel: CryptoViewModel
    private lateinit var cryptoList: ArrayList<CryptoDataClass>
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
            //findNavController().navigate(R.id.action_CryptoFragment_toHomeFragment)
            setAdapter()
        }

        cryptoList = ArrayList<CryptoDataClass>()
        //viewmodel
        cryptoViewModel = ViewModelProvider(this).get(CryptoViewModel::class.java)
        cryptoViewModel.getCrypto()!!.observe(viewLifecycleOwner, Observer {
            addToCryptoList(it)
        })

        recyclerView = view.findViewById(R.id.recyclerview_crypto)
    }

    private fun addToCryptoList(coinDto: CoinDto){
        cryptoList.add(coinDto.toCryptoDataClass())
    }


    fun setAdapter(){
        val c1 = CryptoDataClass("one","1.0")
        val c2 = CryptoDataClass("two", "2.0")
        val list = ArrayList<CryptoDataClass>()
        list.add(c1)
        list.add(c2)

        val cryptoAdapter = CryptoAdapter(cryptoList)

        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = cryptoAdapter
    }

}