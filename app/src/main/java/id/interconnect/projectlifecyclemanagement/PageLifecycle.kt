package id.interconnect.projectlifecyclemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import id.interconnect.projectlifecyclemanagement.Adapter.DescItemAdapter
import id.interconnect.projectlifecyclemanagement.dataclass.ArchitectureDescription
import kotlinx.android.synthetic.main.activity_page_lifecycle.*
import kotlinx.android.synthetic.main.toolbar_blank.*
import java.util.*
import kotlin.collections.ArrayList

class PageLifecycle : AppCompatActivity() {
    lateinit var descItemAdapter: DescItemAdapter

    companion object{
        private const val MANAGER = "manager"
        private const val DEVELOPER = "developer"
        private const val CLIENT = "client"
        private const val ACCEPT = "Accept"
        private const val DECLINE = "Decline"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_lifecycle)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val pageIndicator = intent.getStringExtra("pageIndicator")
        pageLif_main_title.text = pageIndicator

        val dummyItemDescription = ArrayList<ArchitectureDescription>().apply {
            add(ArchitectureDescription("1","hdgcybfbuvbybyfhbvbhbvhbyugbvcubufcvbu1"))
            add(ArchitectureDescription("2","hdgcybfbuvbybyfhbvbhbvhbyugbvcubufcvbu2"))
            add(ArchitectureDescription("3","hdgcybfbuvbybyfhbvbhbvhbyugbvcubufcvbu3"))
            add(ArchitectureDescription("4","hdgcybfbuvbybyfhbvbhbvhbyugbvcubufcvbu4"))
            add(ArchitectureDescription("5","hdgcybfbuvbybyfhbvbhbvhbyugbvcubufcvbu5"))
        }

//        val dummy_Role = DEVELOPER

        descItemAdapter = DescItemAdapter()
        pageArchi_RV.layoutManager = LinearLayoutManager(this)
        pageArchi_RV.adapter = descItemAdapter
        descItemAdapter.setlistMember(dummyItemDescription)

        if(DetailProjectActivity.roleInProject == 1){
            pageLif_btn_edit.text  = ACCEPT
            pageLif_btn_delete.text = DECLINE
        }else if(DetailProjectActivity.roleInProject == 3){
            pageLif_btn_edit.visibility = View.GONE
            pageLif_btn_delete.visibility = View.GONE
        }

        pageLif_btn_edit.setOnClickListener {
            if(DetailProjectActivity.roleInProject == 2){
                val intent = Intent(this,MakeLifecycleActivity::class.java)
                intent.putExtra("editIndicator",pageIndicator)
                startActivity(intent)
            }else{

            }
        }

    }
}