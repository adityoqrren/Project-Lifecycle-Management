package id.interconnect.projectlifecyclemanagement.Adapter

import android.content.Context
import android.util.Log
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.interconnect.projectlifecyclemanagement.dataclass.Project
import id.interconnect.projectlifecyclemanagement.R
import id.interconnect.projectlifecyclemanagement.fragment_detail
import id.interconnect.projectlifecyclemanagement.fragment_lifecycle

class DetailPagerAdapter(private val context: Context, fm:FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    @StringRes
    private val TAB_TITLES = intArrayOf(
        R.string.title_tab_detail_1,
        R.string.title_tab_detail_2
    )
    var project: Project? = null

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0->{
                fragment =
                    fragment_detail.newInstance(
                        project as Project
                    )
            }
            1->{fragment =
                fragment_lifecycle()
            }
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