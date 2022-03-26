package com.example.part_instegram

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser
@ParseClassName("Post")
class Post:ParseObject("Post") {

    fun getDescrptiopn():String?{
           return getString(Key_Description)
    }

    fun setDescripitione(description:String){
        put(Key_Description,description)

    }
    fun getImage():ParseFile?{
        return getParseFile(Key_Image)
    }

    fun setImage(parseFile: ParseFile){
        put(Key_Image,parseFile)
    }


   fun getUser():ParseUser?{
       return getParseUser(Key_user)
   }
   fun setUser(user: ParseUser){
       put(Key_user,user)
   }



    companion object{
        const val Key_Description="description"
        const val Key_Image="Image"
        const val Key_user="User"
    }


}
