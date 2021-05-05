package org.d3if3087.galerihewan.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.d3if3087.galerihewan.R
import org.d3if3087.galerihewan.model.Hewan
import org.d3if3087.galerihewan.network.HewanApi

class MainViewModel : ViewModel() {

    private val data = MutableLiveData<List<Hewan>>()
    private val status = MutableLiveData<HewanApi.ApiStatus>()


    init {
//        data.value = initData()
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch {
            status.value = HewanApi.ApiStatus.LOADING
            try {
//                val result = HewanApi.service.getHewan()
//                Log.d("MainViewModel", "Success: $result")
                data.value = HewanApi.service.getHewan()
                status.value = HewanApi.ApiStatus.SUCCESS
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.value = HewanApi.ApiStatus.FAILED
            }
        }
    }

    // Data ini akan kita ambil dari server di langkah selanjutnya
//    private fun initData(): List<Hewan> {
//        return listOf(
//            Hewan("Angsa", "Cygnus olor", R.drawable.angsa),
//            Hewan("Ayam", "Gallus gallus", R.drawable.ayam),
//            Hewan("Bebek", "Cairina moschata", R.drawable.bebek),
//            Hewan("Domba", "Ovis ammon", R.drawable.domba),
//            Hewan("Kalkun", "Meleagris gallopavo", R.drawable.kalkun),
//            Hewan("Kambing", "Capricornis sumatrensis", R.drawable.kambing),
//            Hewan("Kelinci", "Oryctolagus cuniculus", R.drawable.kelinci),
//            Hewan("Kerbau", "Bubalus bubalis", R.drawable.kerbau),
//            Hewan("Kuda", "Equus caballus", R.drawable.kuda),
//            Hewan("Sapi", "Bos taurus", R.drawable.sapi),
//        )
//    }
    fun getData(): LiveData<List<Hewan>> = data

    fun getStatus(): LiveData<HewanApi.ApiStatus> = status
}