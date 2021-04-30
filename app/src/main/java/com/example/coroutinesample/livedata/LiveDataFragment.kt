package com.example.coroutinesample.livedata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.coroutinesample.R
import com.example.coroutinesample.databinding.LivedataFragmentBinding
import com.example.coroutinesample.repository.UserRepositoryImpl
import com.example.coroutinesample.room.AppDatabase

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
            viewModel.fetchUsers().observe(
                viewLifecycleOwner,
                {
                    viewModel.updateUsers(it)
                }
            )
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
        viewModel.load(UserRepositoryImpl(db.userDaoWithLiveData()))
    }

    private fun initializeRoomDB() {
        db = Room.databaseBuilder(
            requireActivity().applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }
}
