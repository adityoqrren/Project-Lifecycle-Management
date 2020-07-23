package id.interconnect.projectlifecyclemanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    val fragmentHome = fragment_home()
    val fragmentAccount = fragment_account()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        home_BottomNav.setOnNavigationItemSelectedListener(navBottom)

        val dataReceived = intent.getParcelableExtra<Project>("dataNewProject")
        if(dataReceived != null){
           addFragment(fragment_home.newInstance(dataReceived))
        }else{
           addFragment(fragment_home())
        }
    }

    private val navBottom = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.menu_beranda -> {
                addFragment(fragment_home())
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_akun -> {
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
