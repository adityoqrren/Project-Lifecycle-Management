package id.interconnect.projectlifecyclemanagement.lifecycle

import id.interconnect.projectlifecyclemanagement.Api.ApiRepository
import id.interconnect.projectlifecyclemanagement.Api.Result
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import id.interconnect.projectlifecyclemanagement.dataclass.*
import kotlinx.coroutines.Dispatchers

class MyViewModel : ViewModel() {
    private var repository: ApiRepository

    init {
        repository = ApiRepository()
    }

    //User

    fun userRegistration(
        email: String,
        name: String,
        password: String
    ): LiveData<Result<UserRegistrationEditResponse>> {
        return liveData(Dispatchers.IO) {
            val res = repository.userRegistration(email, name, password)
            emit(res)
        }
    }

    fun userLogin(email: String, password: String): LiveData<Result<UserLoginResponse>> {
        return liveData(Dispatchers.IO) {
            val res = repository.userLogin(email, password)
            emit(res)
        }
    }

    fun userEdit(
        email: String,
        name: String,
        password: String,
        old_password: String
    ): LiveData<Result<UserRegistrationEditResponse>> {
        return liveData(Dispatchers.IO) {
            val res = repository.userEdit(email, name, password, old_password)
            emit(res)
        }
    }

    //Project

    fun createProject(
        email: String,
        project_name: String,
        deskripsi: String
    ): LiveData<Result<ProjectCreateResponse>> {
        return liveData(Dispatchers.IO) {
            val res = repository.createProject(email, project_name, deskripsi)
            emit(res)
        }
    }

    fun viewProject(email: String, project_name: String): LiveData<Result<ProjectViewResponse>> {
        return liveData(Dispatchers.IO) {
            val res = repository.viewProject(email, project_name)
            emit(res)
        }
    }

    fun editProject(
        email: String,
        project_name: String,
        deskripsi: String,
        id_project: Int
    ): LiveData<Result<ProjectEditResponse>> {
        return liveData(Dispatchers.IO) {
            val res = repository.editProject(email, project_name, deskripsi, id_project)
            emit(res)
        }
    }

    //Member Project

    fun addMember(email:String, id_member:List<Member>, role:String, id_project:Int) : LiveData<Result<MemberAddResponse>> {
        return liveData(Dispatchers.IO) {
            val res = repository.addMember(email, id_member, role, id_project)
            emit(res)
        }
    }

    fun viewMember(email: String, project_name: String): LiveData<Result<MemberViewResponse>> {
        return liveData(Dispatchers.IO) {
            val res = repository.viewMember(email, project_name)
            emit(res)
        }
    }

    fun deleteMember(
        email: String,
        id_member: String,
        id_project: Int
    ): LiveData<Result<MemberDeleteResponse>> {
        return liveData(Dispatchers.IO) {
            val res = repository.deleteMember(email, id_member, id_project)
            emit(res)
        }
    }

}