package com.example.myapplication.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repository.UserRepository
import com.example.myapplication.room.User
import kotlinx.coroutines.launch

class LiveDataViewModel : ViewModel() {
    val users = MutableLiveData("Fetch Users")
    private lateinit var userRepository: UserRepository
    fun load(userRepository: UserRepository) {
        this.userRepository = userRepository
    }

    fun fetchUsers(): List<User> {
        return userRepository.getAllUsers()
    }

    fun insertMoreUsers() {
        val users = mutableListOf<User>()
        users.add(User(3, "Shekhar", "Dhavan"))
        users.add(User(4, "MS", "Dhoni"))
        viewModelScope.launch {
            userRepository.insertUsers(users)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            userRepository.deleteAllUser()
        }
    }

    fun updateUsers(users: List<User>) {
        var text = ""
        users.forEach {
            text += "${it.firstName} ${it.lastName}\n"
        }
        this.users.postValue(text)
    }
}
