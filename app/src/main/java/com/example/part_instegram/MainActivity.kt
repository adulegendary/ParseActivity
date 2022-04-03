package com.example.part_instegram

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.part_instegram.PackageFragment.ComposeFragment
import com.example.part_instegram.PackageFragment.FeedFragment
import com.example.part_instegram.PackageFragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager: FragmentManager = supportFragmentManager


findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
        item->
    var FragmentShow: Fragment?= null

    when(item.itemId){
        R.id.ComposeID->{
            FragmentShow=ComposeFragment()

        }
        R.id.ProfileID->{
            Logout()
          FragmentShow=ProfileFragment()

        }
        R.id.HomeID->{
            FragmentShow=FeedFragment()

        }


    }
    if(FragmentShow !=null){
     fragmentManager.beginTransaction().replace(R.id.FrameID,FragmentShow).commit()
    }
    true
}

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setSelectedItemId(R.id.HomeID);
//queryPost()

}









companion object {
const val TAG="MainActivity"
}

}

