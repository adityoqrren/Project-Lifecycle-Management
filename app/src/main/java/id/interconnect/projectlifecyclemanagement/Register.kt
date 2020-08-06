package id.interconnect.projectlifecyclemanagement

import Api.Result
import Api.TextIndicatorAPI
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.interconnect.projectlifecyclemanagement.lifecycle.MyViewModel
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_navigate_before_black_24dp)
        supportActionBar?.setDisplayShowTitleEnabled(false)

//        val roleDropdown = arrayOf("Manager", "Developer", "Client")
//
//        val adapter = ArrayAdapter<String>(
//            this, R.layout.dropdown_menu_popup_item, roleDropdown
//        )
//        register_edtRole.setAdapter(adapter)

        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]


        register_btnRegister.setOnClickListener {
            val name = register_edtName.text.toString().trim()
            val email = register_edtEmail.text.toString().trim()
            val password = register_edtPassword.text.toString()

            if(name.isEmpty()){
                register_edtName.error = "Name cannot be empty"
            }
            if(email.isEmpty()){
                register_edtEmail.error = "Email cannot be empty"
            }
            if(password.isEmpty()){
                register_edtPassword.error = "Password cannot be empty"
            }

            if(name.isNotEmpty()&&email.isNotEmpty()&&password.isNotEmpty()) {
                myViewModel.userRegistration(email, name, password)
                    .observe(this, Observer { data ->
                        when (data) {
                            is Result.Success -> {
//                                Toast.makeText(this,data.data.data.name,Toast.LENGTH_LONG).show()
//                                Log.d("HASIL PRINT EMAIL", "ini $email")
//                                Log.d("HASIL PRINT NAMA", "ini $name")
//                                Log.d("HASIL PRINT PASSWORD", "ini $password")
//                                Log.d("HASIL PRINT", "ini hasil printnya ==> ${data.data}")
                                Toast.makeText(
                                    this,
                                    TextIndicatorAPI.textRegisterSuccess,
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(this, Login::class.java)
                                startActivity(intent)
                                finish()
                            }
                            is Result.Error -> {
                                Toast.makeText(
                                    this,
                                    TextIndicatorAPI.textErrorInput,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
            }
        }

    }
}
