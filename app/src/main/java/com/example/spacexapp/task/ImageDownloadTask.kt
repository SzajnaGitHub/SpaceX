package com.example.spacexapp.task

import android.os.AsyncTask
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Picasso
import org.jsoup.Jsoup
import kotlin.properties.Delegates

class ImageDownloadTask(imageView: ImageView, progressBar: ProgressBar) : AsyncTask<String, Int, String>() {
    var imageView: ImageView by Delegates.notNull()
    var progressBar: ProgressBar by Delegates.notNull()

    init {
        //initiate imageView and progressBar
        this.imageView = imageView
        this.progressBar = progressBar
    }

    override fun onPreExecute() {
        super.onPreExecute()
        // Prepare the UI in the main UI thread before executing the task.
        progressBar.visibility = View.VISIBLE
    }

    override fun doInBackground(vararg params: String): String {
        //Scraping images in background
        val document = Jsoup.connect(params[0]).get()
        val elements = document.select("img").first()
        return elements.attr("src")

    }

    override fun onPostExecute(result: String) {
        // Receive the result of the operation done in the new thread in the main UI thread
        println(result)
        progressBar.visibility = View.INVISIBLE

        Picasso.get()
            .load("https:$result")
            .resize(imageView.width, imageView.height)
            .centerCrop()
            .into(imageView)
    }

}