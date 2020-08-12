package id.interconnect.projectlifecyclemanagement

import id.interconnect.projectlifecyclemanagement.Api.Result
import id.interconnect.projectlifecyclemanagement.Api.TextIndicatorAPI
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.interconnect.projectlifecyclemanagement.lifecycle.MyViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

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
                                if(data.data.status==TextIndicatorAPI.textSuccess) {
                                    Toast.makeText(
                                        this,
                                        TextIndicatorAPI.textRegisterSuccess,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val intent = Intent(this, LoginActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }else{
                                    Toast.makeText(this, data.data.message,Toast.LENGTH_LONG).show()
                                }
                            }
                            is Result.Error -> {
                                Toast.makeText(
                                    this,
                                    TextIndicatorAPI.textServerProblem,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
            }
        }

    }
}
