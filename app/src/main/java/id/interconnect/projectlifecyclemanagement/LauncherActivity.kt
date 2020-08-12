package id.interconnect.projectlifecyclemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast

class LauncherActivity : AppCompatActivity() {
    val SPLASH_SCREEN : Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        Handler().postDelayed({
            val userPreferences = UserPreferences(this)
            if(userPreferences.getUser().isNullOrEmpty()){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else {
                val intent= Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            finish()
        },SPLASH_SCREEN)
    }
}
