package id.interconnect.projectlifecyclemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_btnLogin.setOnClickListener(this)
        main_BtnDaftar.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view){
            main_btnLogin -> {
                val intent = Intent(this,Login::class.java)
                startActivity(intent)
            }
            main_BtnDaftar->{
                val intent = Intent(this, Register::class.java)
                startActivity(intent)
            }
        }
    }

}
