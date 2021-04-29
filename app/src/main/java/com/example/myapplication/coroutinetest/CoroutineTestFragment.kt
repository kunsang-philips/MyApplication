package com.example.myapplication.coroutinetest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import kotlinx.android.synthetic.main.main_fragment.buttonLike

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
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        buttonLike.setOnClickListener {
            viewModel.addLikeCount()
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CoroutineTestViewModel::class.java)
    }
}
