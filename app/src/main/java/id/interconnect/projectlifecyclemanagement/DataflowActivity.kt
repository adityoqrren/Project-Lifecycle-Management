package id.interconnect.projectlifecyclemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import id.interconnect.projectlifecyclemanagement.Adapter.DataflowRVAdapter
import id.interconnect.projectlifecyclemanagement.dataclass.Dataflow
import kotlinx.android.synthetic.main.activity_dataflow.*

class DataflowActivity : AppCompatActivity() , DataflowRVAdapter.ItemOnClick {
    var listDataflow = ArrayList<Dataflow>()
    lateinit var dataflowAdapter : DataflowRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dataflow)

        dataflowAdapter = DataflowRVAdapter(this)
        recviewDataflow.layoutManager = LinearLayoutManager(this)
        recviewDataflow.adapter = dataflowAdapter
        dataflowAdapter.setData(listDataflow)

        val dataDataflow = intent?.getParcelableExtra<Dataflow>("dataDataflow")
        if(dataDataflow!=null){
            listDataflow.add(dataDataflow)
        }

        checkIfListEmpty()

        //fab_make_usecase ke activity create menggunakan make architecture
        fab_dataflow.setOnClickListener {
            val intent = Intent(this, MakeArchitecture::class.java)
            intent.putExtra("makeIndicator","Dataflow")
            startActivity(intent)
        }
    }

    override fun onClickNext(dataflow: Dataflow) {
        val intent = Intent(this,PageLifecycle::class.java)
        intent.putExtra("pageIndicator","Dataflow")
        intent.putExtra("data","Dataflow")
        startActivity(intent)
    }

    fun checkIfListEmpty(){
        if(listDataflow.isNotEmpty()){
            fdataflow_bigTitle.visibility = View.VISIBLE
            text_no_dataflow_1.visibility = View.GONE
            text_no_dataflow_2.visibility = View.GONE
        }else{
            fdataflow_bigTitle.visibility = View.GONE
            text_no_dataflow_1.visibility = View.VISIBLE
            text_no_dataflow_2.visibility = View.VISIBLE
        }
    }
}