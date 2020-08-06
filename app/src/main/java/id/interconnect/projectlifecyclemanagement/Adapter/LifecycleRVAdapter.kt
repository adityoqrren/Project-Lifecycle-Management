package id.interconnect.projectlifecyclemanagement.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.interconnect.projectlifecyclemanagement.R
import kotlinx.android.synthetic.main.cardview_item_lifecycle.view.*

class LifecycleRVAdapter(val itemonclick: ItemOnClick) : RecyclerView.Adapter<LifecycleRVAdapter.LifViewHolder>() {
    private var listLifecycle =  ArrayList<String>()

    fun setData(listLifecycle : ArrayList<String>){
        this.listLifecycle = listLifecycle
        notifyDataSetChanged()
    }

    inner class LifViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(lifecycle: String){
            with(itemView){
                item_lifecycle_task.text = lifecycle
                single_card_lifecycle.setOnClickListener {
                    itemonclick.onClickNext(lifecycle)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LifViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.cardview_item_lifecycle,parent,false)
        return LifViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return listLifecycle.size
    }

    override fun onBindViewHolder(holder: LifViewHolder, position: Int) {
        holder.bind(listLifecycle[position])
    }

    interface ItemOnClick{
        fun onClickNext(lifecycle: String)
    }

}