package id.interconnect.projectlifecyclemanagement.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.interconnect.projectlifecyclemanagement.dataclass.Project
import id.interconnect.projectlifecyclemanagement.R
import id.interconnect.projectlifecyclemanagement.dataclass.UserLoginProjects
import kotlinx.android.synthetic.main.cardview_project.view.*

class ProjectRVAdapter(val itemOnClick: ItemOnClick) : RecyclerView.Adapter<ProjectRVAdapter.ProyekRVViewHolder>() {
    private var listProyek =  ArrayList<UserLoginProjects>()

    fun setData(listProject : ArrayList<UserLoginProjects>){
        this.listProyek = listProject
        notifyDataSetChanged()
    }

    inner class ProyekRVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(userLoginProjects: UserLoginProjects){
            with(itemView){
                card_project_title.text = userLoginProjects.project.project_name
                card_project_desc.text = userLoginProjects.project.id_project
                single_cardview_proyek.setOnClickListener {
                    itemOnClick.onClickNext(userLoginProjects)
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
        fun onClickNext(userLoginProjects: UserLoginProjects)
    }
}