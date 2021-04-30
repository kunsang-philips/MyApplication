package com.example.myapplication.unitTesting.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.coroutinesample.R

class CoroutineTestFragment : Fragment() {

    companion object {
        fun newInstance() = CoroutineTestFragment()
    }

    private lateinit var viewModel: CoroutineTestViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.coroutine_test_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CoroutineTestViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
