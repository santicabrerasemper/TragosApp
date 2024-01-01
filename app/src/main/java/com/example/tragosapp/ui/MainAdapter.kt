package com.example.tragosapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tragosapp.R
import com.example.tragosapp.base.BaseViewHolder
import com.example.tragosapp.data.model.Drink
import com.example.tragosapp.data.model.DrinkEntity
import org.w3c.dom.Text

class MainAdapter(private val context: Context, private val drinkList:List<Drink>, private val itemClickListener: OnDrinkClickListener ) :RecyclerView.Adapter<BaseViewHolder<*>> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.drink_item,parent,false))
    }

    override fun getItemCount(): Int {
        return drinkList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder -> holder.bind(drinkList[position], position)
        }
    }

    inner class MainViewHolder(itemView: View): BaseViewHolder<Drink>(itemView) {
        override fun bind(item: Drink, position: Int) {
            Glide.with(context).load(item.image).centerCrop().into(itemView.findViewById(R.id.img_drink))
            itemView.findViewById<TextView>(R.id.txt_title).text = item.name
            itemView.findViewById<TextView>(R.id.txt_description).text = item.description
            itemView.setOnClickListener{
                itemClickListener.onDrinkClick(item,position)
            }
        }
    }

}