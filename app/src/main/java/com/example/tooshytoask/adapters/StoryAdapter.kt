package com.example.tooshytoask.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tooshytoask.Models.StoryCategory
import com.example.tooshytoask.R
import com.example.tooshytoask.activity.story.StoryInfoActivity
import com.google.android.material.imageview.ShapeableImageView

class StoryAdapter(val context: Context, private var storyCategory: ArrayList<StoryCategory>) : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    val alView:ArrayList<ViewHolder> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.status_item_view, parent, false)
        alView.add(ViewHolder(view))
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return storyCategory.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderData = storyCategory[position]
        if (storyCategory != null){

            Glide.with(context).load(storyCategory.get(position).getCategory_img())
                .into(holder.status_img)
            holder.status_title.setText(storyCategory.get(position).getCategory_title())


        }

        holder.status_lay.setOnClickListener{
            context.startActivity(StoryInfoActivity.getStartIntent(context,storyCategory.get(position).category_id));

        }

    }
    /** View holder to initialize ui */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val status_img: ShapeableImageView = itemView.findViewById(R.id.status_img);
        val status_title:TextView = itemView.findViewById(R.id.status_title);
        val lock_fent_img: ImageView = itemView.findViewById(R.id.lock_fent_img);
        val  status_lay: RelativeLayout = itemView.findViewById(R.id.status_lay);





    }
}