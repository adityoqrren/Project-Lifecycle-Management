package id.interconnect.projectlifecyclemanagement.dataclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dataflow (
    var id:Int = 0,
    var desc:String = ""
): Parcelable