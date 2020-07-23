package id.interconnect.projectlifecyclemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_make_project.*

class MakeProject : AppCompatActivity(), MakeProjectDialogFragment.MyDialogListener, MemberRVAdapter.itemOnClick {
    private var myMemberList = ArrayList<Member>()
    lateinit var memberAdapter : MemberRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_project)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        makeProject_btnInvite.setOnClickListener {
            val dialog = MakeProjectDialogFragment()
            dialog.show(supportFragmentManager,"example dialog")
        }

        memberAdapter = MemberRVAdapter(this)
        makeProject_RV.layoutManager = LinearLayoutManager(this)
        makeProject_RV.adapter = memberAdapter

        btn_makeProject.setOnClickListener {
            Log.d("myMemberListSize",myMemberList.size.toString())
            val nameProject = makeProject_edtName.text.toString()
            val descriptionProject = makeProject_edtDesc.text.toString()
            if (myMemberList.size > 0 && nameProject.isNotEmpty() && descriptionProject.isNotEmpty()) {
                val intent = Intent(this, Home::class.java)
                intent.putExtra("dataNewProject", Project(nameProject, descriptionProject))
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Silahkan isi semua data dengan benar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun sendText(nama: String, role: String) {
        myMemberList.add(Member(nama,role))
        memberAdapter.setlistMember(myMemberList)
    }

    override fun onclickcancel(member: Member) {
        myMemberList.remove(member)
        memberAdapter.setlistMember(myMemberList)
    }

}
