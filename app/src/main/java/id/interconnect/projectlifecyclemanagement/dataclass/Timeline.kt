package id.interconnect.projectlifecyclemanagement.dataclass

data class Timeline (
    val id:String = "",
    val id_member:String = "",
    val task:String = "",
    val weight:String = "",
    val start_date:String = "",
    val due_date:String = "",
    val end_date:String = "",
    val note:String = "",
    var status:String = ""
)