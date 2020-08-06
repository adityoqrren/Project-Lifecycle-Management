package id.interconnect.projectlifecyclemanagement.Adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.interconnect.projectlifecyclemanagement.R
import id.interconnect.projectlifecyclemanagement.dataclass.Member
import kotlinx.android.synthetic.main.team_member_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class MemberCircleAdapter : RecyclerView.Adapter<MemberCircleAdapter.MemberCircleVH>() {
    private var ListMember = ArrayList<Member>()
    val colors = arrayListOf<String>(
        "#5E97F6",
        "#9CCC65",
        "#FF8A65",
        "#9E9E9E",
        "#9FA8DA",
        "#90A4AE",
        "#AED581",
        "#F6BF26",
        "#FFA726",
        "#4DD0E1",
        "#BA68C8",
        "#A1887F"
    )

    inner class MemberCircleVH(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(member: Member){
            with(itemView){
                var initial_name = ""
                val words : List<String> = member.name.split(" ")
                Log.d("words size : ", "${words.size}")
                if(words.size<2){
                    initial_name += words[0].get(0)
                }else{
                    for (x in 0..1){
                        initial_name += words[x].get(0)
                    }
                }
                team_member_initial.text = initial_name
                val r = Random()
                val i1: Int = r.nextInt(11 - 0) + 0
                team_member_initial.background.setTint(Color.parseColor(colors.get(i1)))
            }
        }
    }

    fun setListMember(listMember: ArrayList<Member>){
        this.ListMember = listMember
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberCircleVH {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.team_member_item,parent,false)
        return MemberCircleVH(inflater)
    }

    override fun getItemCount(): Int {
        return ListMember.size
    }

    override fun onBindViewHolder(holder: MemberCircleVH, position: Int) {
        holder.bind(ListMember[position])
    }
}