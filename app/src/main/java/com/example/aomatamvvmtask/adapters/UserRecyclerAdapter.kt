package com.example.aomatamvvmtask.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import com.example.aomatamvvmtask.databinding.LayoutItemBinding
import com.example.mvvmwithretrofit.model.movie.Result


class UserRecyclerAdapter(val userList: ArrayList<Result>, onMyItemClickListener: OnMyItemClickListener) : RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>() {

    var  onMyItemClickListener: OnMyItemClickListener

init {
    this.onMyItemClickListener = onMyItemClickListener;
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val productRowBinding: LayoutItemBinding =
            LayoutItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(productRowBinding,onMyItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position])

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(val binding: LayoutItemBinding, onMyItemClickListener: OnMyItemClickListener) : RecyclerView.ViewHolder(binding.root) , View.OnClickListener {
        var  onMyItemClickListener2: OnMyItemClickListener
        init {
            this.onMyItemClickListener2 = onMyItemClickListener
        }
        fun bindItems(movie: Result) {
            binding.movie = movie
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onMyItemClickListener2.onItemClick(adapterPosition)
        }
    }
    interface OnMyItemClickListener{
         fun onItemClick(position: Int)
    }
}