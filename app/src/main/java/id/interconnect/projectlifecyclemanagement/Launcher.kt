package id.interconnect.projectlifecyclemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast

class Launcher : AppCompatActivity() {
    val SPLASH_SCREEN : Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        Handler().postDelayed({
            val userPreferences = UserPreferences(this)
            if(userPreferences.getUser().isNullOrEmpty()){
                Toast.makeText(this,userPreferences.getUser(),Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else {
                val intent= Intent(this, Home::class.java)
                startActivity(intent)
            }
            finish()
        },SPLASH_SCREEN)
    }
}
