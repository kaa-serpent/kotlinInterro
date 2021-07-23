package com.example.chucknoris

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_all_fact.*
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class AllFactActivity : AppCompatActivity() {
    private val TAG = "MyActivity"
    val URL = "https://api.icndb.com/jokes"
    var okHttpClient: OkHttpClient = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_fact)

        randomJokes.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
/*
        val request = ServiceBuilder.buildService( Chuckfacts::class.java)
        val call = request.getfacts()

        call.enqueue(object : Callback<AllFacts>{
            override fun onResponse(call: Call<AllFacts>, response: Response<AllFacts>) {
                if (response.isSuccessful){
                    progress_bar.visibility = View.GONE
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@AllFactsActivity)
                        adapter = MoviesAdapter(response.body()!!.results)
                    }
                }
            }
            override fun onFailure(call: Call<AllFacts>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
*/

        val listView = findViewById<ListView>(R.id.jokes_list)
// 1
        val jokesList = mutableListOf("one", "two", "three", "four") //Recipe.getRecipesFromFile("recipes.json", this)
// 2
        val listItems = arrayOfNulls<String>(jokesList.size)
// 3
        for (i in 0 until jokesList.size) {
            val recipe = jokesList[i]
            listItems[i] = recipe
        }
// 4
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        listView.adapter = adapter
    }

    private fun loadFacts() {
        runOnUiThread {
            progressBar.visibility = View.VISIBLE
        }

        val request: Request = Request.Builder().url(URL).build()
        okHttpClient.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call?, e: IOException?) {
            }

            override fun onResponse(call: Call?, response: Response?) {
                val json = response?.body()?.string()
                // we get the joke from the Web Service
                val txt = (JSONObject(json).getJSONObject("value").get("joke")).toString()

                // we update the UI from the UI Thread
                runOnUiThread {
                    progressBar.visibility = View.GONE
                    // we use Html class to decode html entities
                    factTv.text = Html.fromHtml(txt)
                }
            }
        })

    }
}