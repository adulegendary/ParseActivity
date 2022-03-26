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
import androidx.core.content.FileProvider
import com.parse.*
import java.io.File

class MainActivity : AppCompatActivity() {
    val CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034
    val photoFileName = "photo.jpg"
    var photoFile: File? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 1, set the descrpiton
        // 2, BUTTON TO LAUNCH THE CAMERA
        //3, AN image view to share the picture
        //4, button to save and post the image
        findViewById<Button>(R.id.rcCamera).setOnClickListener{

            onLaunchCamera()
        }
        findViewById<Button>(R.id.rvPost).setOnClickListener{
           // val postImage=findViewById<ImageView>(R.id.rvImage)
            val descrption=findViewById<EditText>(R.id.rvDescrption).text.toString()
            val user= ParseUser.getCurrentUser()
            if(photoFile !=null){
                submitPost(descrption,user,photoFile!!)
            }else{
                Log.e(TAG,"Error because it's null")
            }

        }
        findViewById<Button>(R.id.rvLogOut).setOnClickListener{
         
            val currentUser = ParseUser.getCurrentUser() // this will now be null
            ParseUser.logOut()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }
        queryPost()

    }
fun submitPost(description:String, user: ParseUser,file: File){
        // Create the first object
         val post=Post()
         post.setDescripitione(description)
         post.setUser(user)
        post.setImage(ParseFile(file))
        post.saveInBackground { e ->
            // Something went wrong catch it
             if(e !=null){
                 Log.e(TAG,"Error while loading")
                 e.printStackTrace()
             }else{
                 Log.i(TAG, "Successfully launch")
             }

        }
}
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // by this point we have the camera photo on disk
                val takenImage = BitmapFactory.decodeFile(photoFile!!.absolutePath)
                // RESIZE BITMAP, see section below
                // Load the taken image into a preview
                val ivPreview: ImageView = findViewById(R.id.rvImage)
                ivPreview.setImageBitmap(takenImage)
            } else { // Result was a failure
                Toast.makeText(this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun onLaunchCamera() {
        // create Intent to take a picture and return control to the calling application
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Create a File reference for future access
        photoFile = getPhotoFileUri(photoFileName)

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        if (photoFile != null) {
            val fileProvider: Uri =
                FileProvider.getUriForFile(this, "com.codepath.fileprovider", photoFile!!)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)

            // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
            // So as long as the result is not null, it's safe to use the intent.

            // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
            // So as long as the result is not null, it's safe to use the intent.
            if (intent.resolveActivity(packageManager) != null) {
                // Start the image capture intent to take photo
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE)
            }
        }
    }

    fun getPhotoFileUri(fileName: String): File {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        val mediaStorageDir =
            File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG)

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            Log.d(TAG, "failed to create directory")
        }

        // Return the file target for the photo based on filename
        return File(mediaStorageDir.path + File.separator + fileName)
    }




   fun  queryPost(){
     val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
      query.include(Post.Key_user)
       query.findInBackground(object : FindCallback<Post>{
           override fun done(posts: MutableList<Post>?, e: ParseException?) {
               if(e != null) {
                  Log.e(TAG,"Succesfully Result")
               }else {
                   if(posts != null){
                       for(post in posts){
                           Log.i(TAG,"Post " + post.getDescrptiopn()
                               +" Username " + post.getUser()?.username)

                       }
                   }
               }

           }

       })
       }

    companion object {
        const val TAG="MainActivity"
    }

    }

