package id.interconnect.projectlifecyclemanagement

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Project (
    val title : String = "",
    val description : String =""
//    val ListAnggota : ArrayList<Anggota>
):Parcelable