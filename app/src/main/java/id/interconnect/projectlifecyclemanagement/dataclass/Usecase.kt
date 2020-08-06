package id.interconnect.projectlifecyclemanagement.dataclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Usecase (
    var id : Int = 0,
    var title : String = ""
) : Parcelable