package com.example.kotlinpractice.Crypto

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CryptoRepository {
    val coinDtoLiveData = MutableLiveData<CoinDto>()

    fun getCryptoData(): MutableLiveData<CoinDto> {

        val call = CryptoRetrofitClient.apiInterface.getCrypto()

        call.enqueue(object: Callback<List<CoinDto>> {
            override fun onFailure(call: Call<List<CoinDto>>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : API onfailure", t.message.toString())
            }

            override fun onResponse(
                    call: Call<List<CoinDto>>,
                    response: Response<List<CoinDto>>
            ) {
                // TODO("Not yet implemented")
                if(response.body()!=null){
                    Log.v("DEBUG : API onsuccess", response.body().toString())
                }else{
                    Log.v("DEBUG : API onsuccess", "response body is null")
                }

                val data = response.body()
                data.forEach(){
                    val id = it!!.id
                    val isActive = it!!.isActive
                    val name = it!!.name
                    val rank = it!!.rank
                    val symbol = it!!.symbol

                    val cryptoDto = CoinDto(id,isActive,false,name,rank,symbol,"")

                    coinDtoLiveData.value = cryptoDto
                }

            }
        })

        return coinDtoLiveData
    }
}