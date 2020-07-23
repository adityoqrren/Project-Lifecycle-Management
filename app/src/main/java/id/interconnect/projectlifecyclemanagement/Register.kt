package id.interconnect.projectlifecyclemanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_navigate_before_black_24dp)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val roleDropdown = arrayOf("role 1","role 2","role 3")

        val adapter = ArrayAdapter<String>(
            this,R.layout.dropdown_menu_popup_item,roleDropdown
        )
        register_edtRole.setAdapter(adapter)

    }
}
