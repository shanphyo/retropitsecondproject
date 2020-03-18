package com.mic.webserviceone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.mic.webservice.Adapter.RecyclerData
import com.mic.webservice.Adapter.TitleAdapter
import com.mic.webserviceone.api.Titleinterface
import com.mic.webserviceone.model.title
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() ,RecyclerData{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getTitleone()
        btn_search.setOnClickListener {
            getSearchList()
        }
    }
    fun getSearchList(){
        val BaseUrl="https://jsonplaceholder.typicode.com/"
        var retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var retrofitService=retrofit.create(Titleinterface::class.java)
        var apiCall=retrofitService.getSearchList(et_search.text.toString())
        apiCall.enqueue(object :Callback<title>{
            override fun onFailure(call: Call<title>, t: Throwable) {

            }

            override fun onResponse(call: Call<title>, response: Response<title>) {
                var data=response.body()
                if (data != null) {
                    Log.d("AAAA>>>",data.id.toString())
                }




            }


        })
    }
    fun getTitleone(){
        var datalist:List<title>
        val BaseUrl="https://jsonplaceholder.typicode.com/"
        var retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var retrofitService=retrofit.create(Titleinterface::class.java)
        var apiCall=retrofitService.getTitle()
        apiCall.enqueue(object :Callback<List<title>>{
            override fun onFailure(call: Call<List<title>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<title>>, response: Response<List<title>>) {
               var datalist=response.body()
                var titleAdapt= datalist?.let { TitleAdapter(it,this@MainActivity) }
                rv .layoutManager = LinearLayoutManager(applicationContext)
                rv.adapter = titleAdapt
                Log.d("Reponse>>>>",datalist.toString())

            }

        })

    }

    override fun onFunClick(title: title) {
      val intent=Intent(this@MainActivity,Detail::class.java)
        intent.putExtra("detail",title.body.toString())
        startActivity(intent)
    }
}
