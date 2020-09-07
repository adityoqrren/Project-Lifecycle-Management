package id.interconnect.projectlifecyclemanagement

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.team_member_item.view.*
import java.util.*

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
        val userPreferences = UserPreferences(context!!.applicationContext)
        faccount_email.text = userPreferences.getUser()

//        var initial_name = ""
//        val words : List<String> = member.name.split(" ")
//        Log.d("words size : ", "${words.size}")
//        if(words.size<2){
//            initial_name += words[0].get(0)
//        }else{
//            for (x in 0..1){
//                initial_name += words[x].get(0)
//            }
//        }
//        team_member_initial.text = initial_name
//        val r = Random()
//        val i1: Int = r.nextInt(11 - 0) + 0
//        team_member_initial.background.setTint(Color.parseColor(colors.get(i1)))

        btn_logout.setOnClickListener {
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
