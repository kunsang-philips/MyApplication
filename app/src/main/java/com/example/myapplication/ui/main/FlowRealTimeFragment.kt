package com.example.myapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.databinding.FlowRealTimeFragmentBinding
import kotlinx.android.synthetic.main.livedata_fragment.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FlowRealTimeFragment : Fragment() {

    private lateinit var viewBinder: FlowRealTimeFragmentBinding

    companion object {
        fun newInstance() = FlowRealTimeFragment()
    }

    private lateinit var viewModel: FlowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinder =
            DataBindingUtil.inflate(inflater, R.layout.flow_real_time_fragment, container, false)
        viewBinder.root.buttonFetch.setOnClickListener {
            viewModel.sendOtp()
            lifecycleScope.launch(Dispatchers.Main) {
                viewModel.flowTimer?.collect {
                    viewBinder.message.text = it
                }
            }
        }
        return viewBinder.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FlowViewModel::class.java)
        viewBinder.viewModel = viewModel
        viewBinder.lifecycleOwner = viewLifecycleOwner
        viewBinder.executePendingBindings()
    }

}