package id.interconnect.projectlifecyclemanagement.Api

import android.util.Log
import id.interconnect.projectlifecyclemanagement.dataclass.*
import okhttp3.MultipartBody
import java.lang.IllegalArgumentException
import kotlin.Exception

class ApiRepository {
    private val postApiInterface = ApiClient.getClient().create(ApiInterface::class.java)

    //User

    suspend fun userRegistration(
        email: String,
        name: String,
        password: String
    ): Result<UserRegistrationEditResponse> {
        val body = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("email", email)
            .addFormDataPart("name", name)
            .addFormDataPart("password", password)
            .build()
        return try {
            val res = postApiInterface.userRegistration(body)
                Result.Success(res)
        } catch (e: Exception) {
            Log.d("Errornya","errornya disini : ${e.toString()}")
            Result.Error(e)
        }
    }

    suspend fun userLogin(email: String, password: String): Result<UserLoginResponse> {
        val body = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("email", email)
            .addFormDataPart("password", password)
            .build()

        return try {
            val res = postApiInterface.userLogin(body)
            Result.Success(res)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun userEdit(
        email: String,
        name: String,
        password: String,
        old_password: String
    ): Result<UserRegistrationEditResponse> {
        val body = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("email", email)
            .addFormDataPart("name", name)
            .addFormDataPart("password", password)
            .addFormDataPart("old_password", old_password)
            .build()
        return try {
            val res = postApiInterface.userEdit(body)
            if (res.status == "success") {
                Result.Success(res)
            } else {
                Result.Error(IllegalArgumentException("Not success"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    //Project

    suspend fun createProject(
        email: String,
        project_name: String,
        deskripsi: String
    ): Result<ProjectCreateResponse> {
        val body = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("email", email)
            .addFormDataPart("project_name", project_name)
            .addFormDataPart("deskripsi", deskripsi)
            .build()
        return try {
            val res = postApiInterface.projectCreate(body)
            Result.Success(res)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun viewProject(email: String, project_name: String): Result<ProjectViewResponse> {
        val body = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("email", email)
            .addFormDataPart("project_name", project_name)
            .build()
        return try {
            val res = postApiInterface.projectView(body)
            if (res.status == "success") {
                Result.Success(res)
            } else {
                Result.Error(IllegalArgumentException("Not success"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun editProject(
        email: String,
        project_name: String,
        deskripsi: String,
        id_project: Int
    ): Result<ProjectEditResponse> {
        val body = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("email", email)
            .addFormDataPart("project_name", project_name)
            .addFormDataPart("deskripsi", deskripsi)
            .addFormDataPart("id_project", id_project.toString())
            .build()
        return try {
            val res = postApiInterface.projectEdit(body)
            if (res.status == "success") {
                Result.Success(res)
            } else {
                Result.Error(IllegalArgumentException("Not success"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    //Member Project
    suspend fun addMember(email:String, id_member:List<Member>, role:String, id_project:Int) : Result<MemberAddResponse> {
        val id_member_join = id_member.joinToString(separator = ",")
        val body = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("email",email)
            .addFormDataPart("id_member",id_member_join)
            .addFormDataPart("role",role)
            .addFormDataPart("id_project",id_project.toString())
            .build()
        return try {
            val res = postApiInterface.memberAdd(body)
            if (res.status == "success") {
                Result.Success(res)
            } else {
                Result.Error(IllegalArgumentException("Not success"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun viewMember(email: String, project_name: String): Result<MemberViewResponse> {
        val body = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("email",email)
            .addFormDataPart("project_name",project_name)
            .build()
        return try {
            val res = postApiInterface.memberView(body)
            if (res.status == "success") {
                Result.Success(res)
            } else {
                Result.Error(IllegalArgumentException("Not success"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun deleteMember(
        email: String,
        id_member: String,
        id_project: Int
    ): Result<MemberDeleteResponse> {
        val body = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("email",email)
            .addFormDataPart("id_member",id_member)
            .addFormDataPart("id_project",id_project.toString())
            .build()
        return try {
            val res = postApiInterface.memberDelete(body)
            if (res.status == "success") {
                Result.Success(res)
            } else {
                Result.Error(IllegalArgumentException("Not success"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

//    //Timeline
//
//    suspend fun createTimeline(
//        email: String,
//        id_project: Int,
//        task: String,
//        id_member: String,
//        weight: Int,
//        start_date: String,
//        due_date: String,
//        note: String
//    ): TimelineCreateEditResponse {
//        val body = MultipartBody.Builder()
//            .setType(MultipartBody.FORM)
//            .addFormDataPart("email", email)
//            .addFormDataPart("id_project", id_project.toString())
//            .addFormDataPart("task", task)
//            .addFormDataPart("id_member", id_member)
//            .addFormDataPart("weight", weight.toString())
//            .addFormDataPart("start_date", start_date)
//            .addFormDataPart("due_date", due_date)
//            .addFormDataPart("note", note)
//            .build()
//        val res = postApiInterface.createTimeline(body)
//        return res
//    }
//
//    suspend fun viewTimeline(email: String, id_project: Int): TimelineViewResponse {
//        val res = postApiInterface.viewTimeline(email, id_project)
//        return res
//    }
//
//    suspend fun editTimeline(
//        email: String,
//        id_project: Int,
//        task: String,
//        id_member: String,
//        weight: Int,
//        start_date: String,
//        due_date: String,
//        note: String,
//        status: String
//    ): TimelineCreateEditResponse {
//        val body = MultipartBody.Builder()
//            .setType(MultipartBody.FORM)
//            .addFormDataPart("email", email)
//            .addFormDataPart("id_project", id_project.toString())
//            .addFormDataPart("task", task)
//            .addFormDataPart("id_member", id_member)
//            .addFormDataPart("weight", weight.toString())
//            .addFormDataPart("start_date", start_date)
//            .addFormDataPart("due_date", due_date)
//            .addFormDataPart("note", note)
//            .addFormDataPart("status", status)
//            .build()
//        val res = postApiInterface.editTimeline(body)
//        return res
//    }
}