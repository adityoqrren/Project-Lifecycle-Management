package id.interconnect.projectlifecyclemanagement

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class fragment_home : Fragment(), ProjectRVAdapter.ItemOnClick {
    lateinit var projectAdapter : ProjectRVAdapter
//    var listProject = ArrayList<Project>()
//    var project = Project()

    companion object{
        private var listProject = ArrayList<Project>() //listProject as static variable avoid data to change (as database)
        private const val ARG_PROJECT = "DATA PROJECT"

        fun newInstance(project: Project) : Fragment{
            val fragmentHome = fragment_home()
            val bundle = Bundle()
            bundle.putParcelable(ARG_PROJECT,project)
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
        projectAdapter = ProjectRVAdapter(this)
        recviewBeranda.layoutManager = LinearLayoutManager(activity)
        recviewBeranda.adapter = projectAdapter

        btn_to_make_project.setOnClickListener{
            val intent = Intent(activity,MakeProject::class.java)
            startActivity(intent)
        }

        if(arguments!=null){
            val project_received = arguments?.getParcelable<Project>(ARG_PROJECT) as Project
            listProject.add(project_received)
        }
        projectAdapter.setData(listProject)

        if(listProject.isNotEmpty()){
//            projectAdapter.setData(listProject)
            icon_plm_home.visibility = View.GONE
            text_no_project_1.visibility = View.GONE
            text_no_project_2.visibility = View.GONE
            fhome_bigTitle.visibility = View.VISIBLE
        }else{
            icon_plm_home.visibility = View.VISIBLE
            text_no_project_1.visibility = View.VISIBLE
            text_no_project_2.visibility = View.VISIBLE
            fhome_bigTitle.visibility = View.GONE
        }

    }

    override fun onClickCard(project: Project) {

    }

    override fun onClickNext(project: Project) {
        val intent = Intent(activity, DetailProject::class.java)
        intent.putExtra("to_fragment_detail",project)
        startActivity(intent)
    }

}
