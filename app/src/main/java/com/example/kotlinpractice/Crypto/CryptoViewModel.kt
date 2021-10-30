package com.example.kotlinpractice.Crypto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CryptoViewModel: ViewModel(){

    var cryptoLiveData: MutableLiveData<CoinDto>? = null

    fun getCrypto(): LiveData<CoinDto>? {
        cryptoLiveData = CryptoRepository.getCryptoData()
        return cryptoLiveData
    }
}