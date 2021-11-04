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
import com.example.kotlinpractice.Note.AddNoteDialogFragment
import com.example.kotlinpractice.Note.NoteViewModel
import com.example.kotlinpractice.R
import dagger.hilt.android.AndroidEntryPoint

class CryptoFragment: Fragment(), CryptoAdapter.OnItemClickListener {
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
            findNavController().navigate(R.id.action_CryptoFragment_toHomeFragment)
        }

        cryptoList = ArrayList<CryptoDataClass>()

        //viewmodel
        cryptoViewModel = ViewModelProvider(this).get(CryptoViewModel::class.java)
        cryptoViewModel.getCrypto()!!.observe(viewLifecycleOwner, Observer {
            addToCryptoList(it)
        })

        recyclerView = view.findViewById(R.id.recyclerview_crypto)
    }

    private fun addToCryptoList(coinDtoList: List<CoinDto>){
        coinDtoList.forEach(){
            val id = it!!.id
            val isActive = it!!.isActive
            val name = it!!.name
            val rank = it!!.rank
            val symbol = it!!.symbol

            val cryptoDto = CoinDto(id,isActive,false,name,rank,symbol,"")
            cryptoList.add(cryptoDto.toCryptoDataClass())
        }

        setAdapter()
    }


    private fun setAdapter(){
        val cryptoAdapter = CryptoAdapter(cryptoList,this)

        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = cryptoAdapter

    }

    override fun onItemClick(id: String) {
        //call api and display crypto details
        cryptoViewModel.getCryptoDetails(id)!!.observe(viewLifecycleOwner, Observer {
            showCryptoDetails(it)
        })
    }


    //rank, is_new, is_active, type, description,
    private fun showCryptoDetails(dto: CoinDetailDto){
        var cryptoDetailObj: CryptoDataClass = CryptoDataClass()
        cryptoDetailObj.id = dto.id
        cryptoDetailObj.isActive = dto.isActive
        cryptoDetailObj.isNew = dto.isNew
        cryptoDetailObj.name = dto.name
        cryptoDetailObj.description = dto.description
        cryptoDetailObj.rank = dto.rank
        
        //showCryptoDetails(CryptoDataClass(id = cryptoDetailObj.id))


        val cryptoDetailDialogFragment = CryptoDetailDialogFragment(cryptoDetailObj)
        if(!cryptoDetailDialogFragment.isVisible){
            cryptoDetailDialogFragment.show(parentFragmentManager, "CryptoDetailDialog")
        }
    }



}