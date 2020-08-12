package id.interconnect.projectlifecyclemanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.interconnect.projectlifecyclemanagement.lifecycle.MyViewModel

class ForgotPasswordActivity : AppCompatActivity() {

//    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_navigate_before_black_24dp)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }


}
