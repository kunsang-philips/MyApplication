package com.example.myapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.LivedataFragmentBinding
import kotlinx.android.synthetic.main.livedata_fragment.view.*

class LiveDataFragment : Fragment() {
    private lateinit var viewBinder: LivedataFragmentBinding

    companion object {
        fun newInstance() = LiveDataFragment()
    }

    private lateinit var viewModel: LiveDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinder = DataBindingUtil.inflate(inflater, R.layout.livedata_fragment, container, false)
        viewBinder.root.button.setOnClickListener {
            viewModel.load()
        }
        return viewBinder.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LiveDataViewModel::class.java)
        viewBinder.viewModel = viewModel
        viewBinder.lifecycleOwner = viewLifecycleOwner
        viewBinder.executePendingBindings()
    }

}