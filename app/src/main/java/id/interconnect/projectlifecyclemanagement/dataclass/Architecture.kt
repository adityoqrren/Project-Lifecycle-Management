package id.interconnect.projectlifecyclemanagement.dataclass

data class Architecture (
    val id_architecture_diagram:String = "",
    val link_architexture:String = "",
    val description:Array<ArchitectureDescription>,
    val status:String = ""
)