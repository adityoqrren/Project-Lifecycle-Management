package id.interconnect.projectlifecyclemanagement.dataclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserLoginResponse (
    val status:String,
    val message:String,
    val data:UserLoginData
):Parcelable