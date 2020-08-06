package id.interconnect.projectlifecyclemanagement.dataclass

data class TimelineViewResponse (
    val status:String = "",
    val message:String = "",
    val data:List<Timeline>,
    val email:String = "",
    val id_project:String = "",
    val length:Int = 1
)