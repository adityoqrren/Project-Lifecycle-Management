package id.interconnect.projectlifecyclemanagement

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import id.interconnect.projectlifecyclemanagement.Adapter.DetailPagerAdapter
import id.interconnect.projectlifecyclemanagement.dataclass.Project
import kotlinx.android.synthetic.main.activity_detail_project.*


class DetailProject : AppCompatActivity(),View.OnClickListener {
    lateinit var project: Project
    var isOpen = false
    lateinit var fabOpen : Animation
    lateinit var fabClose : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_project)

        project = intent.getParcelableExtra("to_fragment_detail") as Project
        setSupportActionBar(detail_toolbar_elevated)
        supportActionBar?.title = project.project_name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sectionPagerAdapter =
            DetailPagerAdapter(
                this,
                supportFragmentManager
            )
        sectionPagerAdapter.project = project
        detail_viewPager.adapter = sectionPagerAdapter
        detail_tabs.setupWithViewPager(detail_viewPager)

        fabOpen = AnimationUtils.loadAnimation(this,R.anim.fab_open)
        fabClose = AnimationUtils.loadAnimation(this,R.anim.fab_close)

        detail_viewPager.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if(position==0){
                    lifecycle_main_FAB.visibility = View.GONE
                    if(isOpen) {
                        lif_dark_clicked.visibility = View.GONE
                        isOpen = false
                    }
                }else{
                    lifecycle_main_FAB.setImageDrawable(getDrawable(R.drawable.ic_create_24px))
                    lifecycle_main_FAB.visibility = View.VISIBLE
                }
            }
        })

        lifecycle_main_FAB.setOnClickListener(this)
        btn_to_archi.setOnClickListener(this)
        btn_to_daflow.setOnClickListener(this)
        btn_to_usecase.setOnClickListener(this)
    }

    fun fabToClose(){
        lif_dark_clicked.visibility = View.GONE
        lifecycle_main_FAB.setImageDrawable(getDrawable(R.drawable.ic_create_24px))

        btn_to_sequence.startAnimation(fabClose)
        btn_title_sequence.visibility = View.GONE
        btn_to_sequence.isClickable = false
        btn_to_journey.startAnimation(fabClose)
        btn_title_journey.visibility = View.GONE
        btn_to_journey.isClickable = false
        btn_to_scenario.startAnimation(fabClose)
        btn_title_scenario.visibility = View.GONE
        btn_to_scenario.isClickable = false
        btn_to_daflowstruc.startAnimation(fabClose)
        btn_title_daflowstruc.visibility = View.GONE
        btn_to_daflowstruc.isClickable = false
        btn_to_erd.startAnimation(fabClose)
        btn_title_erd.visibility = View.GONE
        btn_to_erd.isClickable = false
        btn_to_daflow.startAnimation(fabClose)
        btn_title_daflow.visibility = View.GONE
        btn_to_daflow.isClickable = false
        btn_to_usecase.startAnimation(fabClose)
        btn_title_usecase.visibility = View.GONE
        btn_to_usecase.isClickable = false
        btn_to_archi.startAnimation(fabClose)
        btn_title_archi.visibility = View.GONE
        btn_to_archi.isClickable = false

        isOpen = false
    }

    fun fabToOpen(){
        lifecycle_main_FAB.setImageDrawable(getDrawable(R.drawable.ic_close_white_24))
        lif_dark_clicked.visibility = View.VISIBLE
        btn_to_sequence.startAnimation(fabOpen)
        btn_title_sequence.visibility = View.VISIBLE
        btn_to_journey.startAnimation(fabOpen)
        btn_title_journey.visibility = View.VISIBLE
        btn_to_scenario.startAnimation(fabOpen)
        btn_title_scenario.visibility = View.VISIBLE
        btn_to_daflowstruc.startAnimation(fabOpen)
        btn_title_daflowstruc.visibility = View.VISIBLE
        btn_to_erd.startAnimation(fabOpen)
        btn_title_erd.visibility = View.VISIBLE
        btn_to_daflow.startAnimation(fabOpen)
        btn_title_daflow.visibility = View.VISIBLE
        btn_to_usecase.startAnimation(fabOpen)
        btn_title_usecase.visibility = View.VISIBLE
        btn_to_archi.startAnimation(fabOpen)
        btn_title_archi.visibility = View.VISIBLE
        btn_to_daflow.isClickable = true
        btn_to_usecase.isClickable = true
        btn_to_archi.isClickable = true
        isOpen = true
    }


    override fun onClick(view: View) {
        //button for making lifecycle
        val intent = Intent(this,MakeArchitecture::class.java)
        when(view){
            lifecycle_main_FAB->{
                if(isOpen){
                    fabToClose()
                }else{
                    fabToOpen()
                }
            }
            btn_to_archi -> {
                intent.putExtra("makeIndicator","Architecture")
                startActivity(intent)
                fabToClose()
            }
            btn_to_usecase -> {
//                val intent = Intent(this,MakeArchitecture::class.java)
                intent.putExtra("makeIndicator","Usecase")
                startActivity(intent)
                fabToClose()
            }
            btn_to_daflow->{
//                val intent = Intent(this,MakeArchitecture::class.java)
                intent.putExtra("makeIndicator","Dataflow")
                startActivity(intent)
                fabToClose()
            }
        }
    }

}
