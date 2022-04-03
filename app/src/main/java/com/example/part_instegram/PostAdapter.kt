package com.example.part_instegram

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostAdapter(val context: Context, val posts:List<Post>) :RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.item_post,parent,false)
        return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
       val post=posts.get(position)
        holder.bind(post)
    }

    override fun getItemCount(): Int {
     return posts.size
    }
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tvUserName:TextView
        val ivImage: ImageView
        val tvDescrbtion: TextView
      //  val logout: Button
        init {
            tvUserName=itemView.findViewById(R.id.UserNameId)
            ivImage=itemView.findViewById(R.id.pictureID)
            tvDescrbtion=itemView.findViewById(R.id.descrptioneID)
         //   logout=itemView.findViewById(R.id.rvlogout)

        }
        fun bind(post: Post){
            tvDescrbtion.text=post.getDescrptiopn()
            tvUserName.text=post.getDescrptiopn()
            Glide.with(itemView.context).load(post.getImage()?.url).into(ivImage)
        }
    }
}