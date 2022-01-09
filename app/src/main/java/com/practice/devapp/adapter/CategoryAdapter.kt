package com.practice.devapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.practice.devapp.R
import com.practice.devapp.data.Category
import com.practice.devapp.service.MusicPlayerService

class CategoryAdapter(val context: Context, val list: ArrayList<Category>) :
    RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.MyViewHolder {


        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.category_row_item, parent, false)
        )


    }

    override fun onBindViewHolder(holder: CategoryAdapter.MyViewHolder, position: Int) {
        holder.imageView.setImageResource(list.get(position).image)
        holder.imageView.setOnClickListener {
//            Toast.makeText(context, "you have clicked $position", Toast.LENGTH_SHORT).show()
//
//
//            val dialogUtility = DialogUtility(context as MainActivity)
//            dialogUtility.show(context.supportFragmentManager, "CategoryAdapter")

//            val localIntent = Intent().apply {
//                action = "app_is_in_the_background"
//                putExtra("DATA", "app has gone into the background")
//            }

//            val localBroadcastManager = LocalBroadcastManager.getInstance(context)
            context.startService(Intent(context, MusicPlayerService::class.java))

            val localIntent = Intent("MY_CUSTOM_ACTION")
            localIntent.putExtra(
                "DATA",
                "app has gone into the background and local broadcasr has been executed"
            )
            LocalBroadcastManager.getInstance(context).sendBroadcast(localIntent)


        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var imageView: ImageView


        init {
            imageView = itemView.findViewById(R.id.iv)
        }
    }

}