package com.example.part_instegram.PackageFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.part_instegram.MainActivity
import com.example.part_instegram.Post
import com.example.part_instegram.PostAdapter
import com.example.part_instegram.R
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery




open class FeedFragment : Fragment() {


lateinit var postsRecyclerView: RecyclerView
lateinit var adapter: PostAdapter
var allPosts: MutableList<Post> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postsRecyclerView=view.findViewById(R.id.postRecyclerView)

        adapter= PostAdapter(requireContext(),allPosts)
        postsRecyclerView.adapter=adapter

        postsRecyclerView.layoutManager=LinearLayoutManager(requireContext())

        queryPost()
    }
    open fun  queryPost(){
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.Key_user)
        query.addDescendingOrder("createdAt")
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
    companion object{
        const val TAG="FeedFragment"
    }
}