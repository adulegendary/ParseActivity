package com.example.part_instegram.PackageFragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.part_instegram.LoginActivity
import com.example.part_instegram.Post
import com.example.part_instegram.R
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class ProfileFragment : FeedFragment() {


    override fun queryPost(){
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.Key_user)
        query.addDescendingOrder("createdAt")
        query.whereEqualTo(Post.Key_user,ParseUser.getCurrentUser())
        query.findInBackground(object : FindCallback<Post> {


            override fun done(posts: MutableList<Post>?, e: ParseException?) {

                if(e != null) {
                    Log.e(TAG,"Succesfully Result")
                }else {
                    if(posts != null){
                        for(post in posts){
                            Log.i(TAG,"Post " + post.getDescrptiopn()
                                    +" Username " + post.getUser()?.username)

                        }
                        allPosts.addAll(posts)
                        adapter.notifyDataSetChanged()
                    }
                }

            }
        })
    }
}