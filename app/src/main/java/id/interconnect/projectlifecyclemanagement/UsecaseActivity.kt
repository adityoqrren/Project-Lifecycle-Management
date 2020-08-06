package id.interconnect.projectlifecyclemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import id.interconnect.projectlifecyclemanagement.Adapter.UsecaseRVAdapter
import id.interconnect.projectlifecyclemanagement.dataclass.Usecase
import kotlinx.android.synthetic.main.activity_usecase.*

class UsecaseActivity : AppCompatActivity(), UsecaseRVAdapter.ItemOnClick {

//    companion object{
//        val makeUsecaserRequestCode = 200
//    }

    var listUsecase = ArrayList<Usecase>()
    lateinit var usecaseAdapter : UsecaseRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usecase)

        usecaseAdapter = UsecaseRVAdapter(this)
        recviewUsecase.layoutManager = LinearLayoutManager(this)
        recviewUsecase.adapter = usecaseAdapter
        usecaseAdapter.setData(listUsecase)

        val dataUsecase = intent?.getParcelableExtra<Usecase>("dataUsecase")
        if(dataUsecase!=null){
            listUsecase.add(dataUsecase)
        }

        checkIfListEmpty()

        //fab_make_usecase ke activity create menggunakan make architecture
        fab_usecase.setOnClickListener {
            val intent = Intent(this, MakeArchitecture::class.java)
            intent.putExtra("makeIndicator","Usecase")
            startActivity(intent)
        }

    }

    override fun onClickNext(usecase: Usecase) {
        val intent = Intent(this,PageLifecycle::class.java)
        intent.putExtra("pageIndicator","Usecase")
        intent.putExtra("dataUsecase",usecase)
        startActivity(intent)
    }

    fun checkIfListEmpty(){
        if(listUsecase.isNotEmpty()){
            fusecase_bigTitle.visibility = View.VISIBLE
            text_no_usecase_1.visibility = View.GONE
            text_no_usecase_2.visibility = View.GONE
        }else{
            fusecase_bigTitle.visibility = View.GONE
            text_no_usecase_1.visibility = View.VISIBLE
            text_no_usecase_2.visibility = View.VISIBLE
        }
    }
}