package com.evoionosp.windbreaker.ui.components

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

open class RecyclerViewAdapter<T>(
    @LayoutRes val layoutID: Int = 0,
    val viewHolderBindingFunction: (itemData: T, itemView: View, itemPosition: Int) -> Unit
) : androidx.recyclerview.widget.ListAdapter<T, RecyclerViewAdapter.ViewHolder<T>>(DiffCallBack<T>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(layoutID, parent, false)
        return ViewHolder(view)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.itemAnimator = null
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        val itemData = currentList[position]
        holder.bind(itemData, position, viewHolderBindingFunction)
    }

    override fun getItemCount(): Int = currentList.size

    fun setItems(items: List<T>) {
        submitList(items)
    }

    override fun getItem(position: Int): T {
        return super.getItem(position)
    }

    class ViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(itemData: T, position: Int, bindingFunction: (T, View, Int) -> Unit) {
            bindingFunction.invoke(itemData, itemView, position)
        }
    }

    class DiffCallBack<T> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
            return oldItem == newItem
        }
    }

}