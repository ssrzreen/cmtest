package com.example.cmapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.cmapplication.databinding.ActivityDetailBinding
import com.example.cmapplication.model.MobileDetails
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Detail : AppCompatActivity() {

    private  lateinit var  binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        println("onCreate()")
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchCurrencyData().start()


    }
    @SuppressLint("SuspiciousIndentation")
    private fun fetchCurrencyData(): Thread
    {

        val imagesDetails : ImageView = findViewById(R.id.imageD)
        val bundle : Bundle?= intent.extras
        val imagePic = bundle!!.getString("thumbnail")
        Picasso.get().load(imagePic).into(imagesDetails)

        val id = bundle!!.getString("id")

                return Thread{
                    val url = URL("https://dummyjson.com/products/"+id)
                    val connection = url.openConnection() as HttpURLConnection

                    if (connection.responseCode == 200){
                        val inputSystem = connection.inputStream
                        val inputStreReader = InputStreamReader(inputSystem, "UTF-8")
                        val request = Gson().fromJson(inputStreReader, MobileDetails::class.java)
                        updateUI(request)
                        inputStreReader.close()
                        inputSystem.close()
                    }
                    else{
                    }
                }
    }

    private fun updateUI(request: MobileDetails?) {
        runOnUiThread {
            kotlin.run {
                if (request != null) {
                    binding.titleD.text = request.title
                    binding.priceD.text = request.price
                    binding.stockD.text = request.stock
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        println("onStart()")
    }

    override fun onResume() {
        super.onResume()
        println("onResume()")
    }

    override fun onPause() {
        super.onPause()
        println("onPause()")
    }

    override fun onStop() {
        super.onStop()
        println("onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy()")
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart()")
    }

}