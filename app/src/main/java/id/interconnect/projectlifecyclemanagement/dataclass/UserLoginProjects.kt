package id.interconnect.projectlifecyclemanagement.dataclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserLoginProjects (
    val position:String,
    val project:Project
) : Parcelable