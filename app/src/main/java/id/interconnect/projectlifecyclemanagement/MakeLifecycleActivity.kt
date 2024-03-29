package id.interconnect.projectlifecyclemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import id.interconnect.projectlifecyclemanagement.dataclass.Dataflow
import id.interconnect.projectlifecyclemanagement.dataclass.Usecase
import kotlinx.android.synthetic.main.activity_make_lifecycle.*
import kotlinx.android.synthetic.main.add_desc_item.view.*
import kotlinx.android.synthetic.main.toolbar_blank.*

class MakeLifecycleActivity : AppCompatActivity() {
    lateinit var myListDescription : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_lifecycle)
        myListDescription = ArrayList()

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        if(intent.hasExtra("makeIndicator")){
            makeArchi_main_title.text = intent?.getStringExtra("makeIndicator")
            when(intent.getStringExtra("makeIndicator")){
                "Architecture" -> makeArchi_layoutDesc.hint
            }
        }else{
            makeArchi_main_title.text = intent?.getStringExtra("editIndicator")
        }

        makeArchi_btn_plus_desc.setOnClickListener {
            addView()
        }

        makeArchi_btn_save.setOnClickListener {
            if(intent.getStringExtra("makeIndicator")=="Usecase"||intent.getStringExtra("editIndicator")=="Usecase"){
                val intent = Intent(this,UsecaseActivity::class.java)
                intent.putExtra("dataUsecase",Usecase(1,makeArchi_desc.text.toString()))
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }else if(intent.getStringExtra("makeIndicator")=="Dataflow"||intent.getStringExtra("editIndicator")=="Dataflow"){
                val intent = Intent(this,DataflowActivity::class.java)
                intent.putExtra("dataDataflow",Dataflow(1,makeArchi_desc.text.toString()))
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }else{
                val intent = Intent(this,PageLifecycle::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.putExtra("pageIndicator","Architecture")
                startActivity(intent)
            }
            finish()
        }
    }

    private fun addView(){
        val myDescView = layoutInflater.inflate(R.layout.add_desc_item,null,false)
        val btnRemove = myDescView.findViewById<Button>(R.id.makeArchi_itemDesc_delete)
        btnRemove.setOnClickListener {
            removeView(myDescView)
        }
        makeArchi_desc_layout.addView(myDescView)
        makeArchi_btn_save.setOnClickListener {
            if(makeArchi_desc_layout.childCount>0){
                val insideFirstChild = makeArchi_desc_layout.getChildAt(0).makeArchi_itemDesc_edit.text
                if(insideFirstChild.toString().isNotEmpty()){
                    getData()
                }
            }
            Log.d("data list : ",myListDescription.toString())
        }
    }

    private fun removeView(v: View){
        makeArchi_desc_layout.removeView(v)
    }

    private fun getData(){
        myListDescription.clear()
        for(x in 0 until makeArchi_desc_layout.childCount){
            val v = makeArchi_desc_layout.getChildAt(x)
            myListDescription.add(v.makeArchi_itemDesc_edit.text.toString())
        }
    }
}