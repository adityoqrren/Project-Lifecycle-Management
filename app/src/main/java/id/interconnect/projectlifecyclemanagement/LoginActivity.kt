package id.interconnect.projectlifecyclemanagement

import id.interconnect.projectlifecyclemanagement.Api.Result
import id.interconnect.projectlifecyclemanagement.Api.TextIndicatorAPI
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.interconnect.projectlifecyclemanagement.lifecycle.MyViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_navigate_before_black_24dp)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        login_forgotPassword.setOnClickListener{
            val intent = Intent(this,ForgotPasswordActivity::class.java)
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
                                val userPreferences = UserPreferences(this)
                                //pindahin ke class baru dan pakai context.BASE
                                userPreferences.setUser(email,password)
                                Log.d("print data","${dataReceived.data.data}")
//                                Toast.makeText(this,"email : ${userPreferences.getUser()} password: ${userPreferences.getPass()}", Toast.LENGTH_SHORT).show()
//                                Log.d("user preferences","email : ${userPreferences.getUser()} password: ${userPreferences.getPass()}")
                                val intent = Intent(this,HomeActivity::class.java)
//                                intent.putExtra("dataProject",dataReceived.data.data)
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(intent)
                            }else{
                                //semua string harus ada di xml string
                                Log.d("print data","status: ${dataReceived.data.status}")
                                Toast.makeText(this,"Wrong email or password",Toast.LENGTH_LONG).show()
                            }
                        }
                        is Result.Error -> {
                            Toast.makeText(this,TextIndicatorAPI.textServerProblem,Toast.LENGTH_LONG).show()
                        }
                    }
                })
            }
        }


    }
}
