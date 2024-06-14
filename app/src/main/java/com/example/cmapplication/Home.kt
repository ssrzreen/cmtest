package com.example.cmapplication

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.cmapplication.R
import com.example.cmapplication.adapter.MobileDetailsAdapter
import com.example.cmapplication.databinding.ActivityHomeBinding
import com.example.cmapplication.databinding.ActivityMainBinding
import com.example.cmapplication.model.MobileDetails

import org.json.JSONArray
import org.json.JSONObject

class Home : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var context: Context

    private lateinit var requestQueue: RequestQueue
    private val mobileDetailsList = mutableListOf<MobileDetails>()

    private lateinit var stringRequest: StringRequest

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()
        requestJsonData()

    }

    private fun init() {
        recyclerView = findViewById(R.id.mobile_rv)
        context = this@Home
    }

    private fun requestJsonData() {
        requestQueue = Volley.newRequestQueue(context)
        stringRequest = StringRequest(Request.Method.GET,
            "https://dummyjson.com/products",
            { response ->
                try {
                    val jsonObject = JSONObject(response)
                    val jsonArray = jsonObject.getJSONArray("products")
                    fetchTheData(jsonArray)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { volleyError ->
                showToast("API call error")
            })
        requestQueue.add(stringRequest)
    }

    private fun fetchTheData(jsonArray: JSONArray) {
        for (i in 0 until jsonArray.length()) {
            try {
                val mobile = jsonArray.getJSONObject(i)
                mobileDetailsList.add(
                    MobileDetails(
                        mobile.getString("title"),
                        mobile.get("price").toString(),
                        mobile.get("stock").toString(),
                        mobile.get("id").toString(),
                        mobile.getString("thumbnail")
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                showToast("Mobile Detail Error")
            }
        }
        val adapter = MobileDetailsAdapter(mobileDetailsList, context)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}

