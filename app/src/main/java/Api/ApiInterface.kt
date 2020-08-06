package Api

import id.interconnect.projectlifecyclemanagement.dataclass.*
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiInterface {
    //user management
    @POST("/tasks/user/register/")
    suspend fun userRegistration(@Body body: RequestBody): UserRegistrationEditResponse
    @POST("/tasks/user/login/")
    suspend fun userLogin(@Body body: RequestBody):UserLoginResponse
    @PUT("/tasks/user/user/")
    suspend fun userEdit(@Body body: RequestBody):UserRegistrationEditResponse
    //project
    @POST("/tasks/project/create/")
    suspend fun projectCreate(@Body body: RequestBody):ProjectCreateResponse
    @GET("/tasks/project/view/")
    suspend fun projectView(@Body body: RequestBody):ProjectViewResponse
    @PUT("/tasks/project/edit/")
    suspend fun projectEdit(@Body body: RequestBody):ProjectEditResponse
    //Member Project
    @POST("/tasks/project/member/add")
    suspend fun memberAdd(@Body body: RequestBody):MemberAddResponse
    @GET("/tasks/project/member/view")
    suspend fun memberView(@Body body: RequestBody):MemberViewResponse
    @DELETE("/tasks/project/member/delete")
    suspend fun memberDelete(@Body body: RequestBody):MemberDeleteResponse
//    //Timeline
//    @POST("app.plm.<status>.project.timeline")
//    suspend fun createTimeline(@Body body: RequestBody):TimelineCreateEditResponse
//    @GET("app.plm.<status>.project.timeline")
//    suspend fun viewTimeline(
//        @Query("email") email: String,
//        @Query("id_project") id_project: Int
//    ):TimelineViewResponse
//    @PUT("app.plm.<status>.project.timeline")
//    suspend fun editTimeline(@Body body: RequestBody):TimelineCreateEditResponse
//    //Architecture
//    @POST("app.plm.<status>.project.architecture")
//    suspend fun createArchitecture(@Body body: RequestBody):ArchitectureAddEditResponse
//    @GET("app.plm.<status>.project.architecture")
//    suspend fun viewArchitecture(
//        @Query("email") email: String,
//        @Query("id_project") id_project: Int
//    ):ArchitectureViewResponse
//    @PUT("app.plm.<status>.project.architecture")
//    suspend fun editArchitecture(@Body body: RequestBody):ArchitectureAddEditResponse
//    //Architecture Description
//    @POST("app.plm.<status>.project.architecture")
//    suspend fun createArchitectureDesc(@Body body: RequestBody):ArchDescriptionAddEditResponse
//    @PUT("app.plm.<status>.project.architecture")
//    suspend fun editArchitectureDesc(@Body body: RequestBody):ArchDescriptionAddEditResponse
//    @DELETE("gggg/{email}/{id}")
//    suspend fun deleteArchitectureDesc(
//        @Path("email") email: String,
//        @Path("id") id:Int
//    ):MemberDeleteResponse
}