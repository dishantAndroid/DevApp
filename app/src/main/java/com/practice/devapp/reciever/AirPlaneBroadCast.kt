package com.practice.devapp.reciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.practice.devapp.view.ImageShowingActivity


class AirPlaneBroadCast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {


        Toast.makeText(
            context,
            "Dev App broadcast has been executed and timeZone has been changed",
            Toast.LENGTH_LONG
        ).show()

        Log.d(context?.javaClass?.canonicalName, "broadcasr has been executed..")

        when (intent!!.action) {
            "MY_CUSTOM_ACTION" -> {
                val data = intent.getStringExtra("DATA")!!
                Log.d("Your Received data : ", data)
                val intent = Intent(context, ImageShowingActivity::class.java).apply {
                    putExtra("value", "$data")
                    setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context!!.startActivity(intent)
            }
            else -> Toast.makeText(context, "Action Not Found", Toast.LENGTH_LONG).show()
        }

//        val pendingResult = goAsync()
//        val task = Task(pendingResult, intent!!, context!!)
//        task.execute()
//        val intent = Intent(context, ImageShowingActivity::class.java).apply {
//            putExtra("value", "Your internet connection has been changed")
//        }
//        context!!.startActivity(intent)

//        intent?.let {
//            val data = it.extras!!.getString("DATA", "")
//            if (data != null) {
//                val intent = Intent(context, ImageShowingActivity::class.java).apply {
//                    putExtra("value", "$data")
//                }
//                context!!.startActivity(intent)
//            }
//        }
//        val intent = Intent(context, ImageShowingActivity::class.java).apply {
//            putExtra("value", "Your internet connection has been changed")
//        }
//        context!!.startActivity(intent)

    }

//    private class Task(
//        private val pendingResult: PendingResult,
//        private val intent: Intent,
//        private val context: Context
//    ) : AsyncTask<String, Int, String>() {
//
//        override fun doInBackground(vararg params: String?): String {
//
//            context.startActivity(Intent(context, ImageShowingActivity::class.java))
//            return ""
//        }
//
//        override fun onPostExecute(result: String?) {
//            super.onPostExecute(result)
//            // Must call finish() so the BroadcastReceiver can be recycled.
//            pendingResult.finish()
//        }
//    }
}