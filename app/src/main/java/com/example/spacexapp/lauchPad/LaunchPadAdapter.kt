package com.example.spacexapp.lauchPad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_lauchpad.view.*
import android.widget.ProgressBar
import com.example.spacexapp.R
import com.example.spacexapp.task.ImageDownloadTask


class LaunchPadAdapter(val launchpads: List<LaunchPad>, val clickListener: (LaunchPad) -> Unit) :
    RecyclerView.Adapter<LaunchPadAdapter.LaunchPadViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchPadViewHolder {
        return LaunchPadViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_lauchpad, parent, false)
        )
    }

    override fun getItemCount() = launchpads.size

    override fun onBindViewHolder(holder: LaunchPadViewHolder, position: Int) {

        val launchpad = launchpads[position]
        val url = launchpad.wikipedia
        val myImageView: ImageView = holder.itemView.findViewById(R.id.imageView2)
        val myProgressBar: ProgressBar = holder.itemView.findViewById(R.id.progressBar)

        holder.view.textViewTitle.text = launchpad.location.name
        holder.view.textViewStatus.text = launchpad.status

        holder.view.setOnClickListener { clickListener(launchpad) }

        ImageDownloadTask(myImageView, myProgressBar).execute(url)

    }

    class LaunchPadViewHolder(val view: View) : RecyclerView.ViewHolder(view)


}