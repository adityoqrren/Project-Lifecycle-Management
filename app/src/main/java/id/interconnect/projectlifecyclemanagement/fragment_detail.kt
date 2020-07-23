package id.interconnect.projectlifecyclemanagement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A simple [Fragment] subclass.
 */
class fragment_detail : Fragment() {
    lateinit var memberCircleAdapter: MemberCircleAdapter

    companion object{
        private const val ARG_PROJECT = "project"

        fun newInstance(project: Project): fragment_detail{
            val fragmentDetail = fragment_detail()
            val bundle = Bundle()
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
        memberCircleAdapter = MemberCircleAdapter()
        fdetail_memberList_rv.adapter = memberCircleAdapter
        fdetail_memberList_rv.layoutManager = LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false)
        var arrayListMember = ArrayList<Member>().apply {
            this.add(Member("Malian","leader"))
            this.add(Member("Adityo Aji", "android developer"))
            this.add(Member("Nanda Tirana", "front end developer"))
            this.add(Member("M Firqin","back end developer"))
            this.add(Member("Achmad Ichsan", "project manager"))
            this.add(Member("Ichvandi Octa", "software engineer"))
        }
        if(arguments!=null) {
            memberCircleAdapter.setListMember(arrayListMember)
        }
    }

}
