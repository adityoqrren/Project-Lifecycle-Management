package id.interconnect.projectlifecyclemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.interconnect.projectlifecyclemanagement.dataclass.User
import id.interconnect.projectlifecyclemanagement.dataclass.UserLoginData
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    lateinit var dataReceived : UserLoginData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        home_BottomNav.setOnNavigationItemSelectedListener(navBottom)

        dataReceived = intent.getParcelableExtra("dataProject")

        addFragment(fragment_home.newInstance(dataReceived))

        btn_to_make_project.setOnClickListener {
            val intent = Intent(this, MakeProject::class.java)
            startActivity(intent)
        }
    }

    private val navBottom = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.menu_beranda -> {
                addFragment(fragment_home.newInstance(dataReceived))
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_akun -> {
                homeFAB.visibility = View.INVISIBLE
                btn_to_make_project.visibility = View.GONE
                textbtn_to_make_poject.visibility = View.GONE
                addFragment(fragment_account())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment_choosen : Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.home_frame,fragment_choosen).commit()
    }
}
