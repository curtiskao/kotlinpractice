package com.example.kotlinpractice.Crypto

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CryptoRepository {
    val coinDtoLiveData = MutableLiveData<List<CoinDto>>()
    val coinDetailDtoLiveData = SingleLiveEvent<CoinDetailDto>()

    fun getCryptoData(): MutableLiveData<List<CoinDto>> {

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
                coinDtoLiveData.value = data

            }
        })

        return coinDtoLiveData
    }

    fun getCryptoDetail(id: String): SingleLiveEvent<CoinDetailDto> {
        Log.d("rep id",id)
        val call = CryptoRetrofitClient.apiInterface.getCryptoDetails(id)

        call.enqueue(object: Callback<CoinDetailDto> {
            override fun onFailure(call: Call<CoinDetailDto>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : API onfailure", t.message.toString())
            }

            override fun onResponse(
                    call: Call<CoinDetailDto>,
                    response: Response<CoinDetailDto>
            ) {
                // TODO("Not yet implemented")
                if(response.body()!=null){
                    Log.v("DEBUG : API onsuccess", response.body().toString())
                }else{
                    Log.v("DEBUG : API onsuccess", "response body is null")
                }

                val data = response.body()
                coinDetailDtoLiveData.value = data
            }
        })

        return coinDetailDtoLiveData
    }
}