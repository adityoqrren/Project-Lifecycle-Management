package id.interconnect.projectlifecyclemanagement

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class UserPreferences (val context: Context) {
    companion object{
//        private const val PREFS_NAME = "user_login_pref"
        private const val EMAIL_PREF = "email"
        private const val PASS_PREF = "password"
    }

    private fun getPreferences():SharedPreferences{
        val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
        return EncryptedSharedPreferences.create(
                context,
                "user_private_preferences",
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)
    }

    fun setUser(user_email:String,user_password:String){
        getPreferences().edit{
            putString(EMAIL_PREF, user_email)
            putString(PASS_PREF, user_password)
        }
    }

    fun getUser() : String? = getPreferences().getString(EMAIL_PREF,"")

    fun getPass() : String? = getPreferences().getString(PASS_PREF,"")

    fun unsetUser() = getPreferences().edit().remove(EMAIL_PREF).apply()

}