package com.example.taskseven.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskseven.R
import com.example.taskseven.adapters.UserRecyclerAdapter
import com.example.taskseven.api.services.RestClient
import com.example.taskseven.models.ReqResData
import com.example.taskseven.models.User
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RestClient.getRetrofit()
        RestClient.reqResService.getUsers(1, 10)
            .enqueue(object : retrofit2.Callback<ReqResData<List<User>>> {
                override fun onResponse(
                    call: Call<ReqResData<List<User>>>,
                    response: Response<ReqResData<List<User>>>
                ) {
                    val data = response.body()?.data
                    if (data != null) {
                        setAdapter(data)
                    }
                }

                override fun onFailure(call: Call<ReqResData<List<User>>>, t: Throwable) {
                    Log.d("ERROR_LIST_USER", t.toString())
                }
            })
    }

    fun setAdapter(users: List<User>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = UserRecyclerAdapter()
        adapter.setUsers(users)
        recyclerView.adapter = adapter
    }

}