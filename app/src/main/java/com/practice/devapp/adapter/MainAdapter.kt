package com.practice.devapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practice.devapp.R
import com.practice.devapp.data.Category

class MainAdapter(val context: Context, val list: ArrayList<Category>) :
    RecyclerView.Adapter<MainAdapter.MyCustomVIewHolder>() {
    override fun onBindViewHolder(holder: MainAdapter.MyCustomVIewHolder, position: Int) {

        holder.tv_catergory.text = list.get(position).mainTitle
        setCategoryRecyclerView(holder.rv_catergory, list)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainAdapter.MyCustomVIewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false)
        return MyCustomVIewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    inner class MyCustomVIewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var rv_catergory: RecyclerView = itemView.findViewById(R.id.rv_category)
        var tv_catergory: TextView = itemView.findViewById(R.id.tv_id)


    }

    fun setCategoryRecyclerView(recyclerView: RecyclerView, arrayList: ArrayList<Category>) {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = CategoryAdapter(context, list)
    }
}