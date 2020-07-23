package id.interconnect.projectlifecyclemanagement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardview_project.view.*

class ProjectRVAdapter(val itemOnClick: ItemOnClick) : RecyclerView.Adapter<ProjectRVAdapter.ProyekRVViewHolder>() {
    private var listProyek =  ArrayList<Project>()

    fun setData(listProject : ArrayList<Project>){
        this.listProyek = listProject
        notifyDataSetChanged()
    }

    inner class ProyekRVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(project: Project){
            with(itemView){
                card_project_title.text = project.title
                card_project_desc.text = project.description
                btn_proyek_goto.setOnClickListener{
                    itemOnClick.onClickNext(project)
                }
                single_cardview_proyek.setOnClickListener {
                    itemOnClick.onClickCard(project)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProyekRVViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.cardview_project,parent,false)
        return ProyekRVViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return listProyek.size
    }

    override fun onBindViewHolder(holder: ProyekRVViewHolder, position: Int) {
        holder.bind(listProyek[position])
    }

    interface ItemOnClick{
        fun onClickCard(project: Project)
        fun onClickNext(project: Project)
    }
}