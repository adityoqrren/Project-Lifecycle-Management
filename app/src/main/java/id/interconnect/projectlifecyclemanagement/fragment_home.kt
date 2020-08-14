package id.interconnect.projectlifecyclemanagement

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.interconnect.projectlifecyclemanagement.Adapter.ProjectRVAdapter
import id.interconnect.projectlifecyclemanagement.Api.Result
import id.interconnect.projectlifecyclemanagement.Api.TextIndicatorAPI
import id.interconnect.projectlifecyclemanagement.dataclass.UserLoginProjects
import id.interconnect.projectlifecyclemanagement.lifecycle.MyViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class fragment_home : Fragment(), ProjectRVAdapter.ItemOnClick {
    private lateinit var projectAdapter : ProjectRVAdapter
    private lateinit var myViewModel: MyViewModel
//    var listProject = ArrayList<Project>()
//    var project = Project()

    var isOpen = false
    lateinit var fabOpen: Animation
    lateinit var fabClose: Animation
    lateinit var myUserPreferences:UserPreferences

    companion object{
        const val MAKE_PROJECT = 100
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        projectAdapter =
            ProjectRVAdapter(
                this
            )
        recviewBeranda.layoutManager = LinearLayoutManager(activity)
        recviewBeranda.adapter = projectAdapter
        fabOpen = AnimationUtils.loadAnimation(activity,R.anim.fab_open)
        fabClose = AnimationUtils.loadAnimation(activity,R.anim.fab_close)

        activity!!.homeFAB.visibility = View.VISIBLE
        activity!!.btn_to_make_project.visibility = View.INVISIBLE
        activity!!.home_lif_dark_clicked.visibility = View.GONE

        activity!!.btn_to_make_project.setOnClickListener{
            toCloseFab()
            val intent = Intent(activity,MakeProject::class.java)
            startActivityForResult(intent, MAKE_PROJECT)
        }

        myUserPreferences = UserPreferences(activity!!)
        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
//        Log.d("user preferences ","email : ${myUserPreferences.getUser()} password: ${myUserPreferences.getPass()}")
        myViewModel.userLogin(myUserPreferences.getUser() as String, myUserPreferences.getPass() as String).observe(viewLifecycleOwner,
            Observer {
                dataReceived ->
                when(dataReceived){
                    is Result.Success ->{
                        if(dataReceived.data.status == TextIndicatorAPI.textSuccess){
                            if(dataReceived.data.data.projects!=null){
                                if(dataReceived.data.data.projects.isNotEmpty()) {
                                    projectAdapter.setData(dataReceived.data.data.projects)
                                    hideIconBigTitle()
                                }
                            }else{
                                showIconBigTitle()
                            }
                        }else{
                            showIconBigTitle()
                        }
                    }
                    is Result.Error->{
                        Toast.makeText(activity,TextIndicatorAPI.textServerProblem, Toast.LENGTH_LONG).show()
                    }
                }
            })

        activity!!.homeFAB.setOnClickListener {
            if(isOpen){
                toCloseFab()
            }else{
                toOpenFab()
            }
        }

        activity!!.home_lif_dark_clicked.setOnClickListener {
            toCloseFab()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == MAKE_PROJECT){
            if(resultCode==Activity.RESULT_OK){
//                activity!!.finish()
//                startActivity(activity!!.intent)
                myViewModel.userLogin(myUserPreferences.getUser() as String, myUserPreferences.getPass() as String).observe(viewLifecycleOwner,
                    Observer {
                            dataReceived ->
                        when(dataReceived){
                            is Result.Success ->{
                                if(dataReceived.data.status == TextIndicatorAPI.textSuccess){
                                    if(dataReceived.data.data.projects!=null){
                                        if(dataReceived.data.data.projects.isNotEmpty()) {
                                            projectAdapter.setData(dataReceived.data.data.projects)
                                            hideIconBigTitle()
                                        }
                                    }else{
                                        showIconBigTitle()
                                    }
                                }else{
                                    showIconBigTitle()
                                }
                            }
                            is Result.Error->{
                                Toast.makeText(activity,TextIndicatorAPI.textServerProblem, Toast.LENGTH_LONG).show()
                            }
                        }
                    })
            }
        }
    }

    override fun onClickNext(userLoginProjects: UserLoginProjects) {
        val intent = Intent(activity, DetailProjectActivity::class.java)
        intent.putExtra("to_fragment_detail",userLoginProjects.project)
        startActivity(intent)
    }

     private fun showIconBigTitle(){
        icon_plm_home.visibility = View.VISIBLE
        text_no_project_1.visibility = View.VISIBLE
        text_no_project_2.visibility = View.VISIBLE
        fhome_bigTitle.visibility = View.GONE
    }

    private fun hideIconBigTitle(){
        icon_plm_home.visibility = View.GONE
        text_no_project_1.visibility = View.GONE
        text_no_project_2.visibility = View.GONE
        fhome_bigTitle.visibility = View.VISIBLE
    }

    private fun toOpenFab(){
        activity!!.homeFAB.setImageDrawable(activity!!.getDrawable(R.drawable.ic_close_white_24))
        activity!!.btn_to_make_project.startAnimation(fabOpen)
        activity!!.home_lif_dark_clicked.visibility = View.VISIBLE
        activity!!.textbtn_to_make_poject.visibility = View.VISIBLE
        activity!!.btn_to_make_project.isClickable = true
        isOpen = true
    }

    private fun toCloseFab(){
        activity!!.homeFAB.setImageDrawable(activity!!.getDrawable(R.drawable.ic_create_24px))
        activity!!.btn_to_make_project.startAnimation(fabClose)
        activity!!.textbtn_to_make_poject.visibility = View.GONE
        activity!!.home_lif_dark_clicked.visibility = View.GONE
        activity!!.btn_to_make_project.isClickable = false
        isOpen = false
    }
}
