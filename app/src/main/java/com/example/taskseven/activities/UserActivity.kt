package com.example.taskseven.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.taskseven.R
import com.example.taskseven.api.services.RestClient
import com.example.taskseven.databinding.ActivityUserBinding
import com.example.taskseven.models.ReqResData
import com.example.taskseven.models.User
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response

class UserActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userId : Long = intent.getLongExtra("userId", 0)
        RestClient.getRetrofit()
        RestClient.reqResService.getUser(userId)
            .enqueue(
                object : retrofit2.Callback<ReqResData<User>> {
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(
                        call: Call<ReqResData<User>>,
                        response: Response<ReqResData<User>>
                    ) {
                        val data = response.body()?.data
                        if (data != null) {
                            binding.userName.text = "${data.firstName} ${data.lastName}"
                            binding.userEmail.text = data.email
                            binding.userId.text = "ID: ${data.id}"
                            Picasso.get().load(data.avatar)
                                .placeholder(R.drawable.ic_launcher_background)
                                .into(binding.userImage)
                        }
                    }

                    override fun onFailure(call: Call<ReqResData<User>>, t: Throwable) {
                        Log.d("USER_ERROR", t.toString())
                    }

                },
            )
    }
}