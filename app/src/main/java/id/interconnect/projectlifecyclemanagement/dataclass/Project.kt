package id.interconnect.projectlifecyclemanagement.dataclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Project (
    val id_project:String ="",
    val project_name:String = "",
    val description:String = "",
    val role_project:String = "",
    val id_role: String = ""
):Parcelable
