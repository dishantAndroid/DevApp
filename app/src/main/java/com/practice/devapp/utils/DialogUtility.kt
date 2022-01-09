package com.practice.devapp.utils

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.practice.devapp.R

class DialogUtility(val activity: Activity?) : DialogFragment() {

    private var number: Int? = null
//    private lateinit var launcherActivityForResult: ActivityResultLauncher<Intent>

    private val launcherActivityForResult =
        registerForActivityResult(
            ActivityResultContracts.TakePicture()
        ) {
            it.let {
                Toast.makeText(
                    activity,
                    "your image has beeen saved in a given URI",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            val builder = AlertDialog.Builder(it)

            val list = arrayOf("one", "two", "three")

            builder.apply {
                setCancelable(true)
                setIcon(R.drawable.ic_launcher_background)
                setTitle(R.string.app_name)
                setItems(list, DialogInterface.OnClickListener { dialogInterface, i ->
                    Toast.makeText(
                        activity,
                        "you have tapped on ${list.get(i)}",
                        Toast.LENGTH_SHORT
                    ).show()
                })
                setPositiveButton(
                    "OK",
                    DialogInterface.OnClickListener { dialogInterface, i ->


//                        val intent = Intent().apply {
//                            action = Intent.ACTION_VIEW
//                            putExtra(Intent.EXTRA_TEXT, "this is text message")
//                            type = "text/plain"
//                        }
////                        if (intent.resolveActivity(context.packageManager) != null) {
//                        startActivity(intent)
//                        val laucherActivityForResult: ActivityResultLauncher<Intent> =
//                            registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
//                                ActivityResultCallback {
//                                    it?.let {
//                                        it.data
//                                        Toast.makeText(
//                                            context,
//                                            "${it.data.hashCode()}",
//                                            Toast.LENGTH_SHORT
//                                        ).show()
//                                    }
//                                    // doSomeOperation()
//                                })

//                        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                        intent.putExtra(MediaStore.EXTRA_OUTPUT,)
//                        launcherActivityForResult.launch(intent)
//                        }
                        Toast.makeText(activity, "you have tapped on OK button", Toast.LENGTH_SHORT)
                            .show()
                        dialogInterface.dismiss()
                    })
                setNegativeButton(
                    "CANCEL",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        Toast.makeText(
                            activity,
                            "you have tapped on Cancel button",
                            Toast.LENGTH_SHORT
                        ).show()

                        dialogInterface.cancel()
                    })
            }
            return builder.create()

        }

    }


}

