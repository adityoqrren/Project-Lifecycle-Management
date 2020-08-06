package id.interconnect.projectlifecyclemanagement.dataclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserLoginData (
    val email : String,
    val projects : ArrayList<UserLoginProjects>?
):Parcelable