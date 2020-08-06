package id.interconnect.projectlifecyclemanagement

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import id.interconnect.projectlifecyclemanagement.Adapter.LifecycleRVAdapter
import kotlinx.android.synthetic.main.fragment_lifecycle.*

class fragment_lifecycle : Fragment(), LifecycleRVAdapter.ItemOnClick {
    lateinit var myLifecycleAL : ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lifecycle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myLifecycleAL = arrayListOf(
            "Architecture",
            "Usecase",
            "Data Flow",
            "Entity Relationship Diagram",
            "Data Flow Structure",
            "Scenario Test",
            "User Journey",
            "Sequence Diagram"
        )
        val adapter =
            LifecycleRVAdapter(
                this
            )
        lifecycle_recview.layoutManager = LinearLayoutManager(activity)
        lifecycle_recview.adapter = adapter
        adapter.setData(myLifecycleAL)
    }

    override fun onClickNext(lifecycle: String) {
        if(lifecycle.equals("architecture",ignoreCase = true)){
            val intent = Intent(activity, PageLifecycle::class.java)
            intent.putExtra("pageIndicator",lifecycle)
            startActivity(intent)
        }else if(lifecycle.equals("usecase",ignoreCase = true)){
            val intent = Intent(activity, UsecaseActivity::class.java)
            startActivity(intent)
        }else if(lifecycle.equals("data flow",ignoreCase = true)){
            val intent = Intent(activity, DataflowActivity::class.java)
            startActivity(intent)
        }
    }

}
