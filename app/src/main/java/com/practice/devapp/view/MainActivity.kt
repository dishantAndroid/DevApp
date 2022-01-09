package com.practice.devapp.view

import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practice.devapp.R
import com.practice.devapp.adapter.MainAdapter
import com.practice.devapp.data.Category
import com.practice.devapp.reciever.AirPlaneBroadCast

class MainActivity : AppCompatActivity() {

    lateinit var recylerView: RecyclerView
    lateinit var textView: TextView
    lateinit var list: ArrayList<Category>

    lateinit var airPlaneBroadCast: AirPlaneBroadCast

    lateinit var localBroadcastManager: LocalBroadcastManager

    lateinit var intentFilter: IntentFilter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = ArrayList()
        list.add(Category("New Releases", "", R.mipmap.ic_launcher))
        list.add(Category("Last Releases", "", R.mipmap.ic_launcher))
        list.add(Category("Most Watched", "", R.mipmap.ic_launcher))
        list.add(Category("Upcoming", "", R.mipmap.ic_launcher))
        list.add(Category("OutDated", "", R.mipmap.ic_launcher))
        recylerView = findViewById(R.id.rv)
        recylerView.setHasFixedSize(true)
        recylerView.layoutManager = LinearLayoutManager(this)
        recylerView.adapter = MainAdapter(this, list)

//        startActivity(Intent(Intent.ACTION_VIEW))


        localBroadcastManager = LocalBroadcastManager.getInstance(this)
        airPlaneBroadCast = AirPlaneBroadCast()


        intentFilter = IntentFilter("MY_CUSTOM_ACTION")

    }

    override fun onStart() {
        super.onStart()
        localBroadcastManager.registerReceiver(airPlaneBroadCast, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        localBroadcastManager.unregisterReceiver(airPlaneBroadCast)
//
//        val localIntent = Intent().apply {
//            action = "app_is_in_the_background"
//            putExtra("DATA", "app has gone into the background")
//        }
//
//        localBroadcastManager.sendBroadcast(localIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(airPlaneBroadCast)
    }
}