package id.interconnect.projectlifecyclemanagement.Adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.interconnect.projectlifecyclemanagement.R
import kotlinx.android.synthetic.main.add_desc_item.view.*

class DescItemAdapter : RecyclerView.Adapter<DescItemAdapter.MyViewHolder>() {
    private var ListDesc = ArrayList<String>()

//    fun setlistMember(listDesc: ArrayList<String>){
//        this.ListDesc = listDesc
//        notifyDataSetChanged()
//    }

    fun addListMember(listdesc: String){
        ListDesc.add(listdesc)
        notifyDataSetChanged()
    }

    fun getListMember():ArrayList<String>{
        return ListDesc
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(listdesc: String, position: Int){
            with(itemView){
                makeArchi_itemDesc_delete.setOnClickListener {
                    ListDesc.removeAt(position)
                    notifyDataSetChanged()
                }
                makeArchi_itemDesc_edit.addTextChangedListener(object : TextWatcher{
                    override fun afterTextChanged(p0: Editable?) {

                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        ListDesc.set(position,p0.toString())
//                        notifyItemChanged(adapterPosition)
                    }

                })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.add_desc_item,parent,false)
        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return ListDesc.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(ListDesc[position],position)
    }


}