package com.burakaycicek.cryptocurrencyapp.service
import com.burakaycicek.cryptocurrencyapp.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {

    //GET,POST,UPDATE,DELETE

    @GET ("prices?key=0261f7fb314f93d33719c29e9c8512dc0c7f5853")
    fun getData(): Call<List<CryptoModel>>

}