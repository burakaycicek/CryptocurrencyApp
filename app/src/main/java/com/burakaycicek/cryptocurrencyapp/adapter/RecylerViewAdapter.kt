package com.burakaycicek.cryptocurrencyapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.burakaycicek.cryptocurrencyapp.R
import com.burakaycicek.cryptocurrencyapp.model.CryptoModel
import kotlinx.android.synthetic.main.row_layout.view.*

private val colors:  Array<String> = arrayOf("#6e7f80","#536872","#708090","#536878","#36454f")

class RecylerViewAdapter(private val cryptoList : ArrayList<CryptoModel>,private val listener : Listener) : RecyclerView.Adapter<RecylerViewAdapter.RowHolder>() {

    interface  Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }


    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(cryptoModel: CryptoModel,colors:Array<String>, position: Int,listener: Listener){
            itemView.setOnClickListener {
                listener.onItemClick(cryptoModel)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position % 5]))
            itemView.text_name.text= cryptoModel.currency
            itemView.text_price.text=cryptoModel.price
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return  RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {

        holder.bind(cryptoList[position], colors,position,listener)

    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }
}