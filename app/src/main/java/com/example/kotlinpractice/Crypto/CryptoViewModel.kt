package com.example.kotlinpractice.Crypto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CryptoViewModel: ViewModel(){

    var cryptoLiveData: MutableLiveData<List<CoinDto>>? = null

    fun getCrypto(): LiveData<List<CoinDto>>? {
        cryptoLiveData = CryptoRepository.getCryptoData()
        return cryptoLiveData
    }
}