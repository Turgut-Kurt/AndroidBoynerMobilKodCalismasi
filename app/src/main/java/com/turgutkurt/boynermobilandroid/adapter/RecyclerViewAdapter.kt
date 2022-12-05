package com.turgutkurt.boynermobilandroid.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.turgutkurt.boynermobilandroid.databinding.RowLayoutBinding
import com.turgutkurt.boynermobilandroid.model.NewsModel
import com.turgutkurt.boynermobilandroid.view.MainActivity

class RecyclerViewAdapter(private val newsList: ArrayList<NewsModel>, private val listener: MainActivity) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {
    interface Listener {
        fun onItemClick(newsModel: NewsModel)
    }

    private val colors: Array<String> = arrayOf("#13bd27","#29c1e1","#b129e1","#d3df13","#f6bd0c","#a1fb93","#0d9de3","#ffe48f")

    class RowHolder(val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(binding)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onItemClick(newsList.get(position))
        }
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))

        holder.binding.textName.text = newsList.get(position).name
        holder.binding.textDescription.text = newsList.get(position).description

    }

    override fun getItemCount(): Int {
        return newsList.count()
    }
}