package com.emasara.groceryapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.emasara.groceryapp.databinding.ListItemBinding
import com.emasara.groceryapp.model.Grocery

class GroceryAdapter(
    private var data: List<Grocery>,
    private val itemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<GroceryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val grocery = data[position]
        holder.bind(grocery, itemClickListener, position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(data: List<Grocery>) {
        this.data = data
        if (data.isNotEmpty()) {
            notifyItemInserted(0)
        } else {
            notifyDataSetChanged()
        }
    }

    fun orderData() {
        data = data.sortedBy { it.bagColor }
        notifyItemRangeChanged(0, data.size)
    }

    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(grocery: Grocery, itemClickListener: OnItemClickListener, position: Int) {
            binding.model = grocery
            binding.transitionName = "ImageView$position"
            binding.root.setOnClickListener {
                itemClickListener.onItemClick(grocery, binding.circleView)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(socketData: Grocery, circleView: ImageView)
    }
}