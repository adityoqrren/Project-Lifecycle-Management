package id.interconnect.projectlifecyclemanagement

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_account.*

/**
 * A simple [Fragment] subclass.
 */
class fragment_account : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_logout.setOnClickListener {
            val userPreferences = UserPreferences(context!!.applicationContext)
            userPreferences.unsetUser()
            if(userPreferences.getUser().isNullOrEmpty()){
                val intent= Intent(activity,MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }else{
                Toast.makeText(activity,"Error ${userPreferences.getUser()}",Toast.LENGTH_SHORT).show()
            }
        }
    }

}
