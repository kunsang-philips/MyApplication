package com.example.myapplication.livedata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.myapplication.R
import com.example.myapplication.databinding.LivedataFragmentBinding
import com.example.myapplication.repository.UserRepositoryImpl
import com.example.myapplication.room.AppDatabase

class LiveDataFragment : Fragment() {
    private lateinit var viewBinder: LivedataFragmentBinding
    private lateinit var db: AppDatabase

    companion object {
        fun newInstance() = LiveDataFragment()
    }

    private lateinit var viewModel: LiveDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initializeRoomDB()
        viewBinder = DataBindingUtil.inflate(inflater, R.layout.livedata_fragment, container, false)
        viewBinder.buttonFetch.setOnClickListener {
            viewModel.updateUsers(viewModel.fetchUsers())
        }
        viewBinder.buttonInsertMoreUser.setOnClickListener {
            viewModel.insertMoreUsers()
        }
        viewBinder.buttonDeleteAllUsers.setOnClickListener {
            viewModel.deleteAll()
        }
        return viewBinder.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LiveDataViewModel::class.java)
        viewBinder.viewModel = viewModel
        viewBinder.lifecycleOwner = viewLifecycleOwner
        viewBinder.executePendingBindings()
        viewModel.load(UserRepositoryImpl(db.userDao()))
    }

    private fun initializeRoomDB() {
        db = Room.databaseBuilder(
            requireActivity().applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }
}
