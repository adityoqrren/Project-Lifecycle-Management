package id.interconnect.projectlifecyclemanagement.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.interconnect.projectlifecyclemanagement.R
import id.interconnect.projectlifecyclemanagement.dataclass.Dataflow
import kotlinx.android.synthetic.main.cardview_item_usecase.view.*

class DataflowRVAdapter(val itemonclick: ItemOnClick) : RecyclerView.Adapter<DataflowRVAdapter.DataflowViewHolder>() {
    private var listDataflow =  ArrayList<Dataflow>()

    fun setData(listDataflow : ArrayList<Dataflow>){
        this.listDataflow = listDataflow
        notifyDataSetChanged()
    }

    inner class DataflowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(dataflow : Dataflow){
            val title_now = "Dataflow ${adapterPosition+1}"
            with(itemView){
                item_usecase_task.text = title_now
                single_card_usecase.setOnClickListener {
                    itemonclick.onClickNext(dataflow)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataflowViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.cardview_item_usecase,parent,false)
        return DataflowViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return listDataflow.size
    }

    override fun onBindViewHolder(holder: DataflowViewHolder, position: Int) {
        holder.bind(listDataflow[position])
    }

    interface ItemOnClick{
        fun onClickNext(dataflow: Dataflow)
    }

}