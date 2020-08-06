package id.interconnect.projectlifecyclemanagement

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import id.interconnect.projectlifecyclemanagement.Adapter.ProjectRVAdapter
import id.interconnect.projectlifecyclemanagement.dataclass.Project
import id.interconnect.projectlifecyclemanagement.dataclass.UserLoginData
import id.interconnect.projectlifecyclemanagement.dataclass.UserLoginProjects
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class fragment_home : Fragment(), ProjectRVAdapter.ItemOnClick {
    lateinit var projectAdapter : ProjectRVAdapter
//    var listProject = ArrayList<Project>()
//    var project = Project()

    var isOpen = false

    companion object{
//         listProject as static variable avoid data to change (as database)
//         private var listProject = ArrayList<Project>()
        private const val ARG_PROJECT = "DATA PROJECT"

        fun newInstance(userLoginData: UserLoginData) : Fragment{
            val fragmentHome = fragment_home()
            val bundle = Bundle()
            bundle.putParcelable(ARG_PROJECT,userLoginData)
            fragmentHome.arguments = bundle
            return fragmentHome
        }
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
        val fabOpen = AnimationUtils.loadAnimation(activity,R.anim.fab_open)
        val fabClose = AnimationUtils.loadAnimation(activity,R.anim.fab_close)

        activity!!.homeFAB.visibility = View.VISIBLE
        activity!!.btn_to_make_project.visibility = View.INVISIBLE
        activity!!.lif_dark_clicked.visibility = View.GONE

        activity!!.btn_to_make_project.setOnClickListener{
            activity!!.btn_to_make_project.startAnimation(fabClose)
            activity!!.lif_dark_clicked.visibility = View.GONE
            isOpen = false
            val intent = Intent(activity,MakeProject::class.java)
            startActivity(intent)
        }

        if(arguments!=null){
            val project_received = arguments?.getParcelable<UserLoginData>(ARG_PROJECT)
            //periksa data apakah arraylist null atau ada isinya
            if(project_received!!.projects == null){
                showIconBigTitle()
            }else{
                projectAdapter.setData(project_received.projects as ArrayList<UserLoginProjects>)
                hideIconBigTitle()
            }
        }else{
            showIconBigTitle()
        }

        activity!!.homeFAB.setOnClickListener {
            if(isOpen){
                activity!!.btn_to_make_project.startAnimation(fabClose)
                activity!!.textbtn_to_make_poject.visibility = View.GONE
                activity!!.lif_dark_clicked.visibility = View.GONE
                activity!!.btn_to_make_project.isClickable = false
                isOpen = false
            }else{
                activity!!.btn_to_make_project.startAnimation(fabOpen)
                activity!!.lif_dark_clicked.visibility = View.VISIBLE
                activity!!.textbtn_to_make_poject.visibility = View.VISIBLE
                activity!!.btn_to_make_project.isClickable
                isOpen = true
            }
        }


    }

    override fun onClickNext(userLoginProjects: UserLoginProjects) {
        val intent = Intent(activity, DetailProject::class.java)
        intent.putExtra("to_fragment_detail",userLoginProjects)
        startActivity(intent)
    }

    fun showIconBigTitle(){
        icon_plm_home.visibility = View.VISIBLE
        text_no_project_1.visibility = View.VISIBLE
        text_no_project_2.visibility = View.VISIBLE
        fhome_bigTitle.visibility = View.GONE
    }

    fun hideIconBigTitle(){
        icon_plm_home.visibility = View.GONE
        text_no_project_1.visibility = View.GONE
        text_no_project_2.visibility = View.GONE
        fhome_bigTitle.visibility = View.VISIBLE
    }

}
