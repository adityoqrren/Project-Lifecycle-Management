package id.interconnect.projectlifecyclemanagement

import Api.Result
import Api.TextIndicatorAPI
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.interconnect.projectlifecyclemanagement.Adapter.MemberRVAdapter
import id.interconnect.projectlifecyclemanagement.dataclass.Member
import id.interconnect.projectlifecyclemanagement.dataclass.Project
import id.interconnect.projectlifecyclemanagement.lifecycle.MyViewModel
import id.interconnect.projectlifecyclemanagement.uicomponent.MakeProjectDialogFragment
import kotlinx.android.synthetic.main.activity_make_project.*

class MakeProject : AppCompatActivity(), MakeProjectDialogFragment.MyDialogListener, MemberRVAdapter.itemOnClick {
    private var myMemberList = ArrayList<Member>()
    lateinit var memberAdapter : MemberRVAdapter

    private lateinit var myViewModel: MyViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_project)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        makeProject_btnInvite.setOnClickListener {
            val dialog =
                MakeProjectDialogFragment()
            dialog.show(supportFragmentManager,"example dialog")
        }

        memberAdapter =
            MemberRVAdapter(
                this
            )
        makeProject_RV.layoutManager = LinearLayoutManager(this)
        makeProject_RV.adapter = memberAdapter

        btn_makeProject.setOnClickListener {
            Log.d("myMemberListSize",myMemberList.size.toString())
            val nameProject = makeProject_edtName.text.toString()
            val descriptionProject = makeProject_edtDesc.text.toString()
            if (nameProject.isNotEmpty() && descriptionProject.isNotEmpty()) {
                val userPreferences = UserPreferences(this)
                myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
                myViewModel.createProject(userPreferences.getUser() as String,nameProject,descriptionProject).observe(this,
                    Observer {
                        dataReceived ->
                        when(dataReceived){
                            is Result.Success ->{
                                if(dataReceived.data.message == "Success"){
                                    Toast.makeText(this,"Success creating new Project $nameProject",Toast.LENGTH_LONG).show()
                                    finish()
                                }else{
                                    Toast.makeText(this,"Wrong Input. Check Again!",Toast.LENGTH_LONG).show()
                                }
                            }
                            is Result.Error->{
                                Toast.makeText(this,TextIndicatorAPI.textErrorInput,Toast.LENGTH_LONG).show()
                            }
                        }
                    })
                //masukkan member kalau ditambahkan
                if(myMemberList.size>0){

                }
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please fill all the form", Toast.LENGTH_SHORT).show()
                if(nameProject.isEmpty()){
                    makeProject_edtName.error = "Fill the project's name"
                }
                if(descriptionProject.isEmpty()){
                    makeProject_edtDesc.error = "Fill the project's description"
                }
            }
        }
    }

    override fun sendText(nama: String, role: String) {
        myMemberList.add(
            Member(
                nama,
                role
            )
        )
        memberAdapter.setlistMember(myMemberList)
    }

    override fun onclickcancel(member: Member) {
        myMemberList.remove(member)
        memberAdapter.setlistMember(myMemberList)
    }

}
