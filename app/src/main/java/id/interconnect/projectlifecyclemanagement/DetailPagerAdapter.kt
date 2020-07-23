package id.interconnect.projectlifecyclemanagement

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class DetailPagerAdapter(private val context: Context, fm:FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.title_tab_detail_1,R.string.title_tab_detail_2)
    lateinit var project: Project

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0->fragment = fragment_detail.newInstance(project)
            1->fragment = fragment_lifecycle()
        }
        return fragment as Fragment
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }
}