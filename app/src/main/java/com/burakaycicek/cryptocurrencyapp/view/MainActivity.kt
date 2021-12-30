package com.burakaycicek.cryptocurrencyapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burakaycicek.cryptocurrencyapp.R
import com.burakaycicek.cryptocurrencyapp.adapter.RecylerViewAdapter
import com.burakaycicek.cryptocurrencyapp.model.CryptoModel
import com.burakaycicek.cryptocurrencyapp.service.CryptoAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecylerViewAdapter.Listener {

    private val BASE_URL= "https://api.nomics.com/v1/"
    private var cryptoModels: ArrayList<CryptoModel>? = null
    private  var recylerViewAdapter : RecylerViewAdapter? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //https://api.nomics.com/v1/prices?key=0261f7fb314f93d33719c29e9c8512dc0c7f5853

        //RecyclerView

        val layoutManager : RecyclerView.LayoutManager =LinearLayoutManager(this)
        recyclerView.layoutManager =layoutManager

        loadData()

    }

    private fun loadData(){

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()

        call.enqueue(object: Callback<List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        cryptoModels=ArrayList(it)

                        cryptoModels?.let {
                            recylerViewAdapter = RecylerViewAdapter(it,this@MainActivity)
                            recyclerView.adapter = recylerViewAdapter

                        }


                        /*for (cryptoModel: CryptoModel in cryptoModels!!){  //Kontrol ediyoruz cekiyormu cekmiyormu diye
                            println(cryptoModel.currency)
                            println(cryptoModel.price)
                        }

                         */
                    }
                }
            }


            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this,"Clicked : ${cryptoModel.currency}",Toast.LENGTH_LONG).show()
    }

}