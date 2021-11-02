package com.example.kotlinpractice.Crypto

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.*
import java.util.concurrent.atomic.AtomicBoolean

class CryptoViewModel: ViewModel(){

    var cryptoLiveData: MutableLiveData<List<CoinDto>>? = null
    var cryptoDetailLiveData: SingleLiveEvent<CoinDetailDto>? = null

    fun getCrypto(): LiveData<List<CoinDto>>? {
        cryptoLiveData = CryptoRepository.getCryptoData()
        return cryptoLiveData
    }

    fun getCryptoDetails(id: String): SingleLiveEvent<CoinDetailDto>? {
        cryptoDetailLiveData = CryptoRepository.getCryptoDetail(id)
        return cryptoDetailLiveData
    }


}

class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val pending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {

        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }

        // Observe the internal MutableLiveData
        super.observe(owner, Observer<T> { t ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(t: T?) {
        pending.set(true)
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        value = null
    }

    companion object {
        private const val TAG = "SingleLiveEvent"
    }
}