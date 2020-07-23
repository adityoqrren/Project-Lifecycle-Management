package id.interconnect.projectlifecyclemanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail_project.*

class DetailProject : AppCompatActivity() {
    lateinit var project: Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_project)
        project = intent.getParcelableExtra("to_fragment_detail") as Project
        val sectionPagerAdapter = DetailPagerAdapter(this, supportFragmentManager)
        sectionPagerAdapter.project = project
        detail_viewPager.adapter = sectionPagerAdapter
        detail_tabs.setupWithViewPager(detail_viewPager)
    }

}
