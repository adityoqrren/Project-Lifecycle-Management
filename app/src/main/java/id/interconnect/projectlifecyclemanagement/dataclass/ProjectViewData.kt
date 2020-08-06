package id.interconnect.projectlifecyclemanagement.dataclass

data class ProjectViewData (
    val id_project : String,
    val project_name : String,
    val description : String,
    val member : List<MemberOfProjectView>
)