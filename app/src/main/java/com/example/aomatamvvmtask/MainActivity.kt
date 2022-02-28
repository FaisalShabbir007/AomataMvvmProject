package com.example.aomatamvvmtask

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aomatamvvmtask.adapters.UserRecyclerAdapter
import com.example.aomatamvvmtask.databinding.ActivityMainBinding
import com.example.mvvmwithretrofit.model.movie.MoviesList
import com.example.mvvmwithretrofit.model.movie.Result
import com.example.mvvmwithretrofit.repository.Response
import com.example.mvvmwithretrofit.util.Constants
import com.example.mvvmwithretrofit.viewmodels.MainModelViewFactory
import com.example.mvvmwithretrofit.viewmodels.MainViewModel
import android.content.DialogInterface
import android.content.Intent
import com.example.aomatamvvmtask.activities.DetailScreenActivity


class MainActivity : AppCompatActivity(), UserRecyclerAdapter.OnMyItemClickListener {
    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    var movies = ArrayList<Result>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel = ViewModelProvider(
            this,
            MainModelViewFactory(Constants.repository)
        ).get(MainViewModel::class.java)

        //creating our adapter
        val adapter = UserRecyclerAdapter(movies, this)


        //now adding the adapter to recyclerview
        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
        binding.recyclerView.adapter = adapter

        mainViewModel.movieList.observe(this, Observer {
            when (it) {
                is Response.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    it.data?.let {
                        getData(it)
                        adapter.notifyDataSetChanged()
                        binding.progressBar.visibility = View.GONE
//                     Log.d("MYCODE",it.results.toString())
                    }


                }
                is Response.Error -> {
                    Log.d("MYCODE2", resources.getString(R.string.some_error))
                }
            }

        })

        binding.changeTwoCol.setOnClickListener {
            binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        }
        binding.changeThreeCol.setOnClickListener {
            binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
        }


    }

    private fun getData(it: MoviesList) {
        if (it.results.size == 0) {
            binding.txtEmpty.visibility = View.VISIBLE
            binding.txtEmpty.text = resources.getString(R.string.no_data)
        } else {
            it.results.forEach {
                movies.add(it)
            }
            binding.txtEmpty.visibility = View.GONE
        }
    }

    override fun onItemClick(position: Int) {
        dialogBox(position)
    }

    fun dialogBox(position: Int) {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Alert!")
        builder.setMessage("Do you Want to see a picture on full screen?")

        builder.setPositiveButton("YES") { dialog, which ->
            val intent = Intent(this, DetailScreenActivity::class.java)
            intent.putExtra("path", movies.get(position).backdrop_path)
            startActivity(intent)
            dialog.dismiss()
        }

        builder.setNegativeButton(
            "NO"
        ) { dialog, which ->
            dialog.dismiss()
        }

        val alert = builder.create()
        alert.show()
    }
}