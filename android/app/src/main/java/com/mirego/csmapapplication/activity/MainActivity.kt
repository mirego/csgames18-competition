package com.mirego.csmapapplication.activity


import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.ImageButton
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.mirego.csmapapplication.MapPingApplication
import com.mirego.csmapapplication.ObjectSerializer
import com.mirego.csmapapplication.R
import com.mirego.csmapapplication.fragment.ListSegmentFragment
import com.mirego.csmapapplication.fragment.MapSegmentFragment
import com.mirego.csmapapplication.model.Repo
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import javax.inject.Inject
import com.mirego.csmapapplication.service.GitHubService
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.HashMap


class MainActivity : FragmentActivity() {

    private val jsonObject = JSONObject()
    private val nameList = ArrayList<String>()
    private val componentList = ArrayList<String>()
    private val notesList = ArrayList<String>()
    private val typeList = ArrayList<String>()
    private val latList = ArrayList<String>()
    private val lonList = ArrayList<String>()
    private val addressList = ArrayList<String>()

    private val listFragment = ListSegmentFragment()
    private val mapFragment = MapSegmentFragment()
    private var selectedSegmentIndex = 0

    private lateinit var segmentButtons: List<ImageButton>

    @Inject
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MapPingApplication).netComponent.inject(this)

        segmentButtons = listOf(listButton, mapButton, arButton)

        setActionBar(toolbar)

        if (savedInstanceState == null) {
            setupMainView()
        }

        setupButtons()

        downloadData()

        apiCall()
    }

    private fun downloadData() {
        retrofit.create(GitHubService::class.java).listRepos("olivierpineau").enqueue(object : Callback<List<Repo>> {
            override fun onFailure(call: Call<List<Repo>>?, t: Throwable?) {
                Log.d("street's test", "Oops")
            }

            override fun onResponse(call: Call<List<Repo>>?, response: Response<List<Repo>>?) {
                Log.d("street's test", "That's it")
            }
        })
    }

    private fun setupMainView() {
        supportFragmentManager.beginTransaction()
            .add(fragmentRoot.id, listFragment)
            .commit()
    }

    private fun onSegmentButtonClicked(button: ImageButton) {
        when (button) {
            listButton -> {
                replaceFragment(listFragment)
            }

            mapButton -> {
                replaceFragment(mapFragment)
            }

            arButton -> {
                startActivity(Intent(this, ArActivity::class.java))
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(fragmentRoot.id, fragment)
        transaction.commit()
    }

    private fun setupButtons() {
        val listener = View.OnClickListener { view: View ->
            for ((index, segmentButton) in segmentButtons.withIndex()) {
                if (segmentButton == view as ImageButton) {
                    selectedSegmentIndex = index
                    if (view != arButton) {
                        updateSegmentButtonsColor()
                    }
                    onSegmentButtonClicked(segmentButton)
                    break
                }
            }
        }

        for (button in segmentButtons) {
            button.setOnClickListener(listener)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(SELECTED_SEGMENT_INDEX_KEY, selectedSegmentIndex)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.getInt(SELECTED_SEGMENT_INDEX_KEY)?.let {
            selectedSegmentIndex = it
            updateSegmentButtonsColor()
        }
    }

    private fun updateSegmentButtonsColor() {
        for ((index, segmentButton) in segmentButtons.withIndex()) {
            if (index == selectedSegmentIndex) {
                tintSegmentButton(segmentButton, true)
            } else {
                tintSegmentButton(segmentButton, false)
            }
        }
    }

    private fun tintSegmentButton(button: ImageButton, selected: Boolean) {
        button.setColorFilter(
            ContextCompat.getColor(
                this,
                if (selected) R.color.brightSunYellow else R.color.cloudGray
            )
        )
    }

    companion object {
        private const val SELECTED_SEGMENT_INDEX_KEY = "SELECTED_SEGMENT_INDEX_KEY"
    }

    private fun apiCall() {
        val downloadTask = DownloadTask()
        var result: String? = null
        try {
            result = downloadTask.execute("https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json").get()
            val received = JSONArray(result)
            for(entry: Int in 0..received.length()-1) {
                //Log.i("Response", received.get(entry).toString())
                var entrydata = JSONObject(received.get(entry).toString())
                var name = entrydata.get("name").toString()
                var component = entrydata.get("component").toString()
                var notes = entrydata.get("notes").toString()
                var type = entrydata.get("type").toString()
                var lat = entrydata.get("lat").toString()
                var lon = entrydata.get("lon").toString()
                var address = entrydata.get("address").toString()

                nameList.add(name)
                componentList.add(component)
                notesList.add(notes)
                typeList.add(type)
                latList.add(lat)
                lonList.add(lon)
                addressList.add(address)

                val sharedPreferences = this.getSharedPreferences("com.mirego.csmapapplication.activity", Context.MODE_PRIVATE)
                sharedPreferences.edit().putString("nameList", ObjectSerializer.serialize(nameList)).apply()
                sharedPreferences.edit().putString("componentList", ObjectSerializer.serialize(componentList)).apply()
                sharedPreferences.edit().putString("notesList", ObjectSerializer.serialize(notesList)).apply()
                sharedPreferences.edit().putString("typeList", ObjectSerializer.serialize(typeList)).apply()
                sharedPreferences.edit().putString("latList", ObjectSerializer.serialize(latList)).apply()
                sharedPreferences.edit().putString("lonList", ObjectSerializer.serialize(lonList)).apply()
                sharedPreferences.edit().putString("addressList", ObjectSerializer.serialize(addressList)).apply()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    inner class DownloadTask : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg urls: String): String {
            var result = ""
            val url: URL
            val urlConnection: HttpURLConnection
            try {
                url = URL(urls[0])
                urlConnection = url.openConnection() as HttpURLConnection
                val `in` = urlConnection.inputStream
                val reader = InputStreamReader(`in`)
                var data = reader.read()
                while (data != -1) {
                    val current = data.toChar()
                    result += current
                    data = reader.read()
                }
                return result
            } catch (e: Exception) {
                e.printStackTrace()
                return "Failed"
            }
        }
    }
}
