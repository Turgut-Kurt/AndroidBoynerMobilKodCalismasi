package com.turgutkurt.boynermobilandroid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.turgutkurt.boynermobilandroid.adapter.RecyclerViewAdapter
import com.turgutkurt.boynermobilandroid.databinding.ActivityMainBinding
import com.turgutkurt.boynermobilandroid.model.NewsModel
import com.turgutkurt.boynermobilandroid.model.NewsResponseModel
import com.turgutkurt.boynermobilandroid.services.NewsAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val baseUrl="https://newsapi.org/v2/"
    private val API_KEY="05a4aed1abf242549d4a156b1c382cb0"
    private var newsResponseModels : ArrayList<NewsResponseModel>? =null
    private var newsModels : ArrayList<NewsModel>? =null
    private var recyclerViewAdapter : RecyclerViewAdapter? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var layoutManager : RecyclerView.LayoutManager= LinearLayoutManager(this)
        binding.recyclerView.layoutManager=layoutManager


        loadData();
    }

    private fun loadData(){
        println("load data çalıştı")
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service=retrofit.create(NewsAPI::class.java)
        val call = service.getSources("en",API_KEY)
        println("call geçti")
        call.enqueue( object: Callback<NewsResponseModel> {
            override fun onResponse(
                call: Call<NewsResponseModel>,
                response: Response<NewsResponseModel>
            ) {
                println("Response girdi ${response}")
                if (response.isSuccessful){
                    println("response.isSuccessful girdi")
                    println("response.isSuccessful ${response.body()?.getSources()}")
                    println("response.isSuccessful ${response.body()?.getStatus()}")
                    response.body()?.setStatus("deneme")
                    println("response.isSuccessful ${response.body()?.getStatus()}")

                    //eğer ki bu boş gelmediyse bu işlemi yap
                     response.body()?.getSources().let{
                       newsModels = ArrayList(it)
                        newsModels.let {
                            recyclerViewAdapter=RecyclerViewAdapter(newsModels!!,this@MainActivity)
                            binding.recyclerView.adapter=recyclerViewAdapter
                        }
                         if (it != null) {
                             for(item in it){
                                 println("asdf ${item.name}")
                             }
                         }

                    }
                }
            }

            override fun onFailure(call: Call<NewsResponseModel>, t: Throwable) {
                println("onFailure girdi ${t}")
                Log.e("s","${t.toString()}")
                t.printStackTrace()
            }


        })
    }

     fun onItemClick(newsModel: NewsModel) {
        TODO("Not yet implemented")
    }
    /*
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final APIInterface apiService = ApiClient.getClient().create(APIInterface.class);
        Call<ResponseModel> call = apiService.getLatestNews(“techcrunch”,API_KEY);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel>call, Response<ResponseModel> response) {
                if(response.body().getStatus().equals(“ok”)) {
                List<Article> articleList = response.body().getArticles();
                if(articleList.size()>0) {
                }
            }
            }
            @Override
            public void onFailure(Call<ResponseModel>call, Throwable t) {
                Log.e(“out”, t.toString());
            }
        });
    }
     */

}