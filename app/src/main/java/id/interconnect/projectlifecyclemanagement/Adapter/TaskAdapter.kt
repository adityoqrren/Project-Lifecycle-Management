package id.interconnect.projecttimelinemanagement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.digitalcreative.coronaapps.utils.AnimUtil
import id.interconnect.projectlifecyclemanagement.R
import id.interconnect.projectlifecyclemanagement.dataclass.Timeline
import kotlinx.android.synthetic.main.content_task_fdetail.view.*
import kotlinx.android.synthetic.main.cardview_detail_item.view.*

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.LifViewHolder>() {
    private var listTimeline =  ArrayList<Timeline>()

    fun setData(listTimeline : ArrayList<Timeline>){
        this.listTimeline = listTimeline
        notifyDataSetChanged()
    }

    inner class LifViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(timeline: Timeline){
            with(itemView){
                fdetail_taskKind.text = timeline.task
                fdetail_name.text = timeline.id_member
                content_fdetail_datestart.text = timeline.start_date
                content_fdetail_dateend.text = timeline.end_date
                content_fdetail_datedue.text = timeline.due_date
                content_fdetail_cweight.text = timeline.weight
                content_fdetail_note.text = timeline.note
                //for expanding and collaping detail
                detail_card_task.setOnClickListener {
                    with(layout_included_expand) expand@{
                        if (AnimUtil.isExpanded(this)) {
                            AnimUtil.animateContentCollapse(this)
                            AnimUtil.animateRotate(this@with.detail_card_task.fdetail_img_open_task, AnimUtil.TYPE_COLLAPSE)
                        } else {
                            AnimUtil.animateContentExpand(this)
                            AnimUtil.animateRotate(this@with.fdetail_img_open_task, AnimUtil.TYPE_EXPAND)
                        }
                    }
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.LifViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.cardview_detail_item,parent,false)
        return LifViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return listTimeline.size
    }

    override fun onBindViewHolder(holder: LifViewHolder, position: Int) {
        holder.bind(listTimeline[position])
    }

}