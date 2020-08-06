package id.interconnect.projectlifecyclemanagement.dataclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Member (
    val name:String = "",
    val position:String = ""
) : Parcelable