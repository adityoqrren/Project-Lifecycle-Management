package id.interconnect.projectlifecyclemanagement

import android.app.Activity
import id.interconnect.projectlifecyclemanagement.Api.Result
import id.interconnect.projectlifecyclemanagement.Api.TextIndicatorAPI
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.interconnect.projectlifecyclemanagement.Adapter.MemberRVAdapter
import id.interconnect.projectlifecyclemanagement.dataclass.Member
import id.interconnect.projectlifecyclemanagement.lifecycle.MyViewModel
import id.interconnect.projectlifecyclemanagement.uicomponent.MakeProjectDialogFragment
import kotlinx.android.synthetic.main.activity_make_project.*

class MakeProject : AppCompatActivity(), MakeProjectDialogFragment.MyDialogListener, MemberRVAdapter.itemOnClick {
    private var myMemberList = ArrayList<Member>()
    private lateinit var memberAdapter : MemberRVAdapter

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
                                if(dataReceived.data.status == TextIndicatorAPI.textSuccess){
                                    Toast.makeText(this,"Success creating new Project $nameProject",Toast.LENGTH_LONG).show()
//                                    val intent = Intent(this,HomeActivity::class.java)
//                                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                                    setResult(Activity.RESULT_OK)
                                    finish()
                                }else{
                                    Toast.makeText(this,"Wrong Input. Check Again!",Toast.LENGTH_LONG).show()
                                    setResult(Activity.RESULT_CANCELED)
                                }
                            }
                            is Result.Error->{
                                Toast.makeText(this,TextIndicatorAPI.textServerProblem,Toast.LENGTH_LONG).show()
                                setResult(Activity.RESULT_CANCELED)
                            }
                        }
                    })
                //masukkan member kalau ditambahkan
                if(myMemberList.size>0){

                }
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
