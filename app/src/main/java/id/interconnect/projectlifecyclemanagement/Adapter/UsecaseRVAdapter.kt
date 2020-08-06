package id.interconnect.projectlifecyclemanagement.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.interconnect.projectlifecyclemanagement.R
import id.interconnect.projectlifecyclemanagement.dataclass.Usecase
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.cardview_item_usecase.view.*

class UsecaseRVAdapter(val itemonclick: ItemOnClick) : RecyclerView.Adapter<UsecaseRVAdapter.UsecaseViewHolder>() {
    private var listUsecase =  ArrayList<Usecase>()

    fun setData(listUsecase : ArrayList<Usecase>){
        this.listUsecase = listUsecase
        notifyDataSetChanged()
    }

    inner class UsecaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(usecase : Usecase){
            val title_now = "Usecase ${adapterPosition+1}"
            with(itemView){
                item_usecase_task.text = title_now
                single_card_usecase.setOnClickListener {
                    itemonclick.onClickNext(usecase)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsecaseViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.cardview_item_usecase,parent,false)
        return UsecaseViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return listUsecase.size
    }

    override fun onBindViewHolder(holder: UsecaseViewHolder, position: Int) {
        holder.bind(listUsecase[position])
    }

    interface ItemOnClick{
        fun onClickNext(usecase: Usecase)
    }

}