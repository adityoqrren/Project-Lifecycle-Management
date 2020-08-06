package id.interconnect.projectlifecyclemanagement.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.interconnect.projectlifecyclemanagement.dataclass.Member
import id.interconnect.projectlifecyclemanagement.R
import kotlinx.android.synthetic.main.cardview_item.view.*

class MemberRVAdapter(val onclikInterface : itemOnClick) : RecyclerView.Adapter<MemberRVAdapter.MyViewHolder>() {
    private var listMember = ArrayList<Member>()

    fun setlistMember(listMember: ArrayList<Member>){
        this.listMember = listMember
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(member: Member){
            with(itemView){
                card_memberName.text = member.name
                card_memberRole.text = member.position
                dialog_invite_btnDelete.setOnClickListener {
                    onclikInterface.onclickcancel(member)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.cardview_item,parent,false)
        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return listMember.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listMember[position])
    }

    interface itemOnClick{
        fun onclickcancel(member: Member)
    }

}