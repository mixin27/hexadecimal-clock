package com.norm.hexadecimalclock

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var myTask: MyTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "Color = ${Utils.getHexTime()}")

        // TODO: improve app performance.
        val handler = Handler()
        val timer =  Timer()
        val task = object : TimerTask() {
            override fun run() {
                handler.post {
                    myTask = MyTask(this@MainActivity).execute(Utils.getHexTime()) as MyTask
                }
            }
        }
        timer.schedule(task, 0, 1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "AsyncTask is canceled")
        myTask.cancel(true)
    }

    @SuppressLint("StaticFieldLeak")
    inner class MyTask(
        activity: MainActivity
    ): AsyncTask<String, Void, String>() {

        var weakReference: WeakReference<MainActivity> = WeakReference(activity)

        override fun doInBackground(vararg params: String?): String {
            return params[0]!!
        }
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            weakReference.get()!!.main_layout.setBackgroundColor(Color.parseColor(result))
            weakReference.get()!!.tv_color_hex.text = result
            if (result!!.hexIsLight()) {
                weakReference.get()!!.tv_color_hex.setTextColor(Color.parseColor("#000000"))
            } else {
                weakReference.get()!!.tv_color_hex.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }
    }
}
