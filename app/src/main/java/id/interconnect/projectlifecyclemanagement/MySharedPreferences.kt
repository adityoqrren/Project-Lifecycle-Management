package id.interconnect.projectlifecyclemanagement

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context: Context) {

    companion object{
        private const val PREFS_NAME = "user_login_pref"
        private const val EMAIL = "email"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setUser(user_email:String){
        val editor = preferences.edit()
        editor.putString(EMAIL, user_email)
        editor.apply()
    }

    fun getUser() : String{
        return preferences.getString(EMAIL,"") as String
    }

    fun unsetUser(){
        val editor = preferences.edit()
        editor.remove(EMAIL)
        editor.apply()
    }

}