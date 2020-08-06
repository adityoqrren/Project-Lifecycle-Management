package id.interconnect.projectlifecyclemanagement

import Api.Result
import Api.TextIndicatorAPI
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.interconnect.projectlifecyclemanagement.lifecycle.MyViewModel
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_navigate_before_black_24dp)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        login_forgotPassword.setOnClickListener{
            val intent = Intent(this,ForgotPassword::class.java)
            startActivity(intent)
        }

        login_btnLogin.setOnClickListener{
            val email = login_edtEmail.text.toString().trim()
            val password = login_edtPassword.text.toString()
            if(email.isEmpty()){
                login_edtEmail.error = "email cannot be empty"
            }
            if(password.isEmpty()){
                login_edtPassword.error = "password cannot be empty"
            }
            if(email.isNotEmpty() && password.isNotEmpty()){
                myViewModel = ViewModelProvider(this)[MyViewModel::class.java]

                myViewModel.userLogin(email, password).observe(this, Observer { dataReceived ->
                    when(dataReceived){
                        is Result.Success -> {
                            //data message const val
                            if(dataReceived.data.status == TextIndicatorAPI.textSuccess ){
//                                val userPreferences = UserPreferences(this)
                                //pindahin ke class baru dan pakai context.BASE
//                                userPreferences.setUser(dataReceived.data.data.email)
                                Toast.makeText(this,"email: ${dataReceived.data.data.email}",Toast.LENGTH_LONG).show()
                                Log.d("print data","${dataReceived.data.data}")
//                                val intent = Intent(this,Home::class.java)
//                                intent.putExtra("dataProject",dataReceived.data.data)
//                                startActivity(intent)
                            }else{
                                //semua string harus ada di xml string
                                Toast.makeText(this,"Wrong email or password",Toast.LENGTH_LONG).show()
                            }
                        }
                        is Result.Error -> {
                            Toast.makeText(this,"There is a problem. Wait a moment later.",Toast.LENGTH_LONG).show()
                        }
                    }
                })
            }
        }


    }
}
