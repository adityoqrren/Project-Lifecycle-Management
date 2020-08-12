package id.interconnect.projectlifecyclemanagement

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.digitalcreative.coronaapps.utils.AnimUtil
import id.interconnect.projectlifecyclemanagement.Adapter.MemberCircleAdapter
import id.interconnect.projectlifecyclemanagement.dataclass.Member
import id.interconnect.projectlifecyclemanagement.dataclass.Project
import id.interconnect.projectlifecyclemanagement.dataclass.Timeline
import id.interconnect.projectlifecyclemanagement.uicomponent.MakeProjectDialogFragment
import id.interconnect.projecttimelinemanagement.TaskAdapter
import kotlinx.android.synthetic.main.description_list.*
import kotlinx.android.synthetic.main.fragment_detail.*


/**
 * A simple [Fragment] subclass.
 */
class fragment_detail : Fragment(), MakeProjectDialogFragment.MyDialogListener{
    lateinit var myTimelineList: ArrayList<Timeline>

    companion object{
        private const val ARG_PROJECT = "project"

        fun newInstance(project: Project): fragment_detail{
            val fragmentDetail = fragment_detail()
            val bundle = Bundle()
//            Log.d("Argument project",project.description)
            bundle.putParcelable(ARG_PROJECT, project)
            fragmentDetail.arguments = bundle
            return fragmentDetail
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val memberCircleAdapter = MemberCircleAdapter()
        fdetail_memberList_rv.adapter = memberCircleAdapter
        fdetail_memberList_rv.layoutManager = LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false)

        val taskAdapter = TaskAdapter()
        fdetail_RV.layoutManager = LinearLayoutManager(activity)
        fdetail_RV.adapter = taskAdapter

        //dummy
        val arrayListMember = ArrayList<Member>().apply {
            this.add(
                Member(
                    "Malian",
                    "leader"
                )
            )
            this.add(
                Member(
                    "Adityo Aji",
                    "android developer"
                )
            )
            this.add(
                Member(
                    "Nanda Tirana",
                    "front end developer"
                )
            )
            this.add(
                Member(
                    "M Firqin",
                    "back end developer"
                )
            )
            this.add(
                Member(
                    "Achmad Ichsan",
                    "project manager"
                )
            )
            this.add(
                Member(
                    "Ichvandi Octa",
                    "software engineer"
                )
            )
        }
        myTimelineList = arrayListOf(
            Timeline(
                "1",
                "Iwany",
                "Architecture",
                "10",
                "08/04/20",
                "20/08/20",
                "20/08/20",
                "Kerjain ya!"
            ),
            Timeline(
                "2",
                "Abdi",
                "Usecase",
                "10",
                "08/04/20",
                "20/08/20",
                "20/08/20",
                "Kerjain ya!"
            ),
            Timeline(
                "3",
                "Lohan",
                "Dataflow",
                "10",
                "08/04/20",
                "20/08/20",
                "20/08/20",
                "Kerjain ya!"
            )
        )

        if(arguments!=null) {
            val myProject = arguments?.getParcelable<Project>(ARG_PROJECT)
            Log.d("data","datanya $myProject")
            if(myProject!=null){
//                card_desc_1_tv.text = myProject.description
                memberCircleAdapter.setListMember(arrayListMember)
                taskAdapter.setData(myTimelineList)
            }
        }

        //for expanding and collapsing description
        btn_desc_1_all.setOnClickListener {
            if(card_desc_1_tv.maxLines == 2){
                AnimUtil.animateRotate(btn_desc_1_all, AnimUtil.TYPE_EXPAND)
                AnimUtil.animateExpandTextLine(card_desc_1_tv)
            }else{
                AnimUtil.animateRotate(btn_desc_1_all, AnimUtil.TYPE_COLLAPSE)
                AnimUtil.animateCollapseTextLine(card_desc_1_tv,resources)
            }
        }

        fdetail_makeTask.setOnClickListener {
            val intent = Intent(activity, MakeTaskActivity::class.java)
            startActivity(intent)
        }

        //not yet
        fdetail_btn_tambahMember.setOnClickListener {
            val dialog = MakeProjectDialogFragment()
            dialog.setTargetFragment(this, 1)
            dialog.show(fragmentManager as FragmentManager,"add member dialog")
        }

    }

    override fun sendText(name: String, role: String) {
        Toast.makeText(activity, "name: $name and role: $role", Toast.LENGTH_LONG).show()
    }

}
