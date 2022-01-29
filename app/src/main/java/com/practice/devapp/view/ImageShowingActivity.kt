package com.practice.devapp.view

import android.app.job.JobScheduler
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.practice.devapp.R
import com.practice.devapp.databinding.ActivityImageShowingBinding
import com.practice.devapp.service.MusicPlayerIntentService
import com.practice.devapp.utils.PickRingtone
import java.io.File
import java.io.IOException
import java.util.concurrent.Executors

class ImageShowingActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    private lateinit var musicPlayerIntentService: MusicPlayerIntentService

    private lateinit var jobScheduler: JobScheduler

    private val JOB_ID: Int = 101

    var stringBuilder = StringBuilder()

    lateinit var imageShowingBinding: ActivityImageShowingBinding

    lateinit var context: Context

    lateinit var uri: String
    lateinit var file: File

    var arrayList: ArrayList<Bitmap> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageShowingBinding = ActivityImageShowingBinding.inflate(layoutInflater)
        setContentView(imageShowingBinding.root)

//        musicPlayerIntentService = MusicPlayerIntentService()

        context = this
        jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler

        intent?.apply {
            var text = extras!!.getString("value", "")

            imageShowingBinding.tvText.text = text

//            startService(Intent(this@ImageShowingActivity, MusicPlayerService::class.java))

//            imageShowingBinding.ivImage.setImageBitmap(image)

        }


        file = File(filesDir, "image")
        uri = file.absolutePath


        val takePhoto = registerForActivityResult(ActivityResultContracts.TakePicturePreview())
        {

            if (saveImageToInternalStorage("internalImages", it!!)) {
                Toast.makeText(this, "image saved successfully", Toast.LENGTH_SHORT)
                getImagefromInternalStorage()
            } else {
                Toast.makeText(this, "image doesn't get saved", Toast.LENGTH_SHORT)

            }
//            Log.d("URIOFImage", "${it.path}")
//            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, it)
//            imageShowingBinding.ivImage.setImageBitmap(it)

//            imageShowingBinding.ivImage.setImageBitmap(bitmap)


//            if (it) {
//            getImagefromInternalStorage()
//            }
        }


        val customchoosePhoto =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            {
                if (it.resultCode == RESULT_OK) {

                    val uri = it.data!!.dataString

                    Log.d("imageURi", "$uri")
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, Uri.parse(uri))
                    imageShowingBinding.ivImage.setImageBitmap(bitmap)
                }
            }

        imageShowingBinding.btnStart.setOnClickListener {
//            startService(Intent(this, MusicPlayerIntentService::class.java))

//            val intent = Intent(this, MusicPlayerJobIntentService::class.java)
//            MusicPlayerJobIntentService.enqueueWork(this, intent)

//            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE)
//
//            val registerActivity =
//                registerForActivityResult(ActivityResultContracts.TakePicturePreview())
//                {
//                    imageShowingBinding.ivImage.setImageBitmap(it)
//                }
//
//            registerActivity.launch(intent)

//            allowPermission()

//            val componentName = ComponentName(this, MusicPlayerJobService::class.java)
//
//            val jobInfo = JobInfo.Builder(JOB_ID, componentName)
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
//
//                .setPersisted(true)
//                .build()
//
//            var result = jobScheduler.schedule(jobInfo)
//
//            if (result == JobScheduler.RESULT_SUCCESS) {
//                Toast.makeText(this, "job has been scheduled", Toast.LENGTH_SHORT).show()
//            } else if (result == JobScheduler.RESULT_FAILURE) {
//                Toast.makeText(this, "job hasn't been able to scheduled", Toast.LENGTH_SHORT).show()
//
//            }
//
//            imageShowingBinding.tvText.setText("MusicPlayerIntent Service has been started...")

//            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            getresult.launch("image/*")
//            getresult.launch("*/*")

//            getresult.launch(intent)


//            file = File(filesDir, "image")
//            uri = file.absolutePath

//            val contextWrapper=ContextWrapper(this)
//            contextWrapper.getd
//
//            val uri = "/document/image:2000"


//            val contextWrapper = ContextWrapper(applicationContext)
//
//            val directory = contextWrapper.getDir("appImages", Context.MODE_PRIVATE)
//            file = File(directory, "appInternalImage${System.currentTimeMillis()}")
//
//            Log.d("URIOFImage", "${file.absolutePath}")
//            preview.launch(Uri.parse(file.absolutePath))


//            preview.launch()

//            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            takePhoto.launch()

//            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            customchoosePhoto.launch()

//            val chooseIntent = Intent(Intent.ACTION_PICK).apply {
//
//            }
//            chooseIntent.type = "image/*"
//
//            val intent = Intent.createChooser(chooseIntent, "Want to select a pic")
//            customchoosePhoto.launch(intent)

//            startActivityfor(intent)

            pickRingtone.launch(1)

        }
        imageShowingBinding.btnStop.setOnClickListener {

//            MusicPlayerIntentService.stopMyIntentService()
//            imageShowingBinding.tvText.setText("MusicPlayerIntent Service has been stopped...")
//            jobScheduler.cancel(JOB_ID)

//            val result = "3 updahyay"
//            updatedExistingContacts(result)

//            getImagefromInternalStorage()
        }

//        imageShowingBinding.tvText.setOnClickListener {
//            stopService(Intent(this, MusicPlayerService::class.java))
//        }


    }

    fun allowPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED && (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.READ_CONTACTS,
                    android.Manifest.permission.WRITE_CONTACTS
                ),
                1
            )
        } else {
//            readContacts()
            supportLoaderManager.initLoader(1, null, this)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.size > 0) {
//            readContacts()

            supportLoaderManager.initLoader(1, null, this)
        }
    }

    fun readContacts() {
        var handler = Handler(Looper.myLooper()!!)

        Executors.newSingleThreadExecutor().execute {

            val cursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrayOf(
                    ContactsContract.Contacts.DISPLAY_NAME,
                    ContactsContract.Contacts._ID,
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                ),
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    stringBuilder.append(
                        cursor.getString(0) + "," + cursor.getString(1) + "," + cursor.getString(
                            2
                        )
                    )

                }
            }

            handler.post {
                if (stringBuilder.toString().length > 0) {
                    imageShowingBinding.tvText.setText(stringBuilder)
                } else {
                    imageShowingBinding.tvText.setText("No contacts found")
                }
            }

        }
    }


    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {

        return CursorLoader(
            this, ContactsContract.CommonDataKinds.Phone.CONTENT_URI, arrayOf(
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts._ID,
                ContactsContract.CommonDataKinds.Phone.NUMBER
            ), null, null, null
        )
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {

        if (data != null && data.count > 0) {
            while (data.moveToNext()) {
                stringBuilder.append(
                    data.getString(0) + "," + data.getString(1) + "," + data.getString(
                        2
                    )
                )
            }
            imageShowingBinding.tvText.setText(stringBuilder.toString())
        } else {
            imageShowingBinding.tvText.setText("No contacts found")


        }

    }

    override fun onLoaderReset(loader: Loader<Cursor>) {

    }

    fun updatedExistingContacts(result: String) {
        val array = result.split(" ")
        val id = array.get(0)
        val updatedName = array.get(1)
        val contentValues = ContentValues()
        contentValues.put(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY, updatedName)
        val whereClause = ContactsContract.RawContacts._ID + " = ?"

        val selectionArgs = arrayOf(id)

        contentResolver.update(
            ContactsContract.RawContacts.CONTENT_URI,
            contentValues,
            whereClause,
            selectionArgs
        )
    }

    val getresult = registerForActivityResult(ActivityResultContracts.GetContent())
    {
//        val photo = MediaStore.Images.Media.getBitmap(contentResolver, it)
//        imageShowingBinding.ivImage.setImageBitmap(photo)
    }

//    val startactiivtyForResult =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
//        {
//            val result = it.data?.data
//            if (result != null) {
//                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, result)
//                imageShowingBinding.ivImage.setImageBitmap(bitmap)
//            }
//        }


    fun saveImageToInternalStorage(fileName: String, bitmap: Bitmap): Boolean {
        return try {
            openFileOutput("$fileName.jpg", MODE_PRIVATE).use { stream ->
                if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 95, stream)) {
                    throw IOException("couldn't save image")
                }
            }
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    fun getImagefromInternalStorage() {

        var handler = Handler(Looper.getMainLooper())

        Executors.newSingleThreadExecutor().execute {

//            val bitmap = BitmapFactory.decodeFile(file.absolutePath)


            val files = filesDir.listFiles()
            files.filter {
                it.canRead() && it.isFile && it.name.endsWith("internalImages.jpg")
            }.map {
                val bytes = it.readBytes()
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

                arrayList.add(bitmap)
            }



            handler.post {
                if (arrayList.size > 0) {
                    imageShowingBinding.ivImage.setImageBitmap(arrayList.get(0))

                } else {
                    imageShowingBinding.ivImage.setImageDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.ic_launcher_background
                        )
                    )
                }

            }

        }


    }

    val pickRingtone = registerForActivityResult(PickRingtone())
    {
        if (it != null) {
            val mediaPlayer = MediaPlayer.create(context, it)
            mediaPlayer.isLooping = false
            mediaPlayer.start()
        }
    }
}