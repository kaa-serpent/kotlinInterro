package com.example.chucknoris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    // use the ICNDB API for Chuck Norris Facts
    val URL = "https://api.icndb.com/jokes/random"
    var okHttpClient: OkHttpClient = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nextBtn.setOnClickListener {
            loadRandomFact()
        }
        allJokes.setOnClickListener {
            val intent = Intent(this, AllFactActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadRandomFact() {
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