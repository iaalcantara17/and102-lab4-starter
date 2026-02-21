package com.codepath.campgrounds

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

private const val TAG = "CampgroundAdapter"

class CampgroundAdapter(private val context: Context, private val campgrounds: List<Campground>) :
    RecyclerView.Adapter<CampgroundAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_campground, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO: Get the individual campground and bind to holder
        val campground = campgrounds[position]
        holder.bind(campground)
    }

    override fun getItemCount() = campgrounds.size // Fix me!

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        // TODO: Create member variables for any view that will be set
        private val nameTextView: TextView = itemView.findViewById(R.id.campgroundName)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.campgroundDescription)
        private val locationTextView: TextView = itemView.findViewById(R.id.campgroundLocation)
        private val imageView: ImageView = itemView.findViewById(R.id.campgroundImage)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(campground: Campground) {
            // TODO: Set item views based on views and data model
            nameTextView.text = campground.name.orEmpty()
            descriptionTextView.text = campground.description.orEmpty()
            locationTextView.text = campground.latLong.orEmpty()

            Glide.with(context)
                .load(campground.imageUrl)
                .into(imageView)
        }

        override fun onClick(v: View?) {
            // TODO: Get selected campground
            val position = absoluteAdapterPosition
            if (position == RecyclerView.NO_POSITION) return
            val campground = campgrounds[position]

            // TODO: Navigate to Details screen and pass selected campground
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(CAMPGROUND_EXTRA, campground)
            context.startActivity(intent)
        }
    }
}