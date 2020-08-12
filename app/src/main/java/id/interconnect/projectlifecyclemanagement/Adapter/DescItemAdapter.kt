package id.interconnect.projectlifecyclemanagement.Adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.interconnect.projectlifecyclemanagement.R
import id.interconnect.projectlifecyclemanagement.dataclass.ArchitectureDescription
import kotlinx.android.synthetic.main.add_desc_item.view.*
import kotlinx.android.synthetic.main.description_list.view.*

class DescItemAdapter : RecyclerView.Adapter<DescItemAdapter.MyViewHolder>() {
    private var ListDesc = ArrayList<ArchitectureDescription>()

    fun setlistMember(listDesc: ArrayList<ArchitectureDescription>){
        this.ListDesc = listDesc
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(description: ArchitectureDescription){
            with(itemView){
                desc_number.text = (adapterPosition+1).toString()
                desc_item_text.text = description.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.description_list,parent,false)
        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return ListDesc.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(ListDesc[position])
    }
}