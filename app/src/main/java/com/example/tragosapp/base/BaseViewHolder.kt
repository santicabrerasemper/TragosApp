package com.example.tragosapp.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class BaseViewHolder<T>(itemView: View): ViewHolder(itemView) {
    abstract fun bind(item: T, position: Int)

}