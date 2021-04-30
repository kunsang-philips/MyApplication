package com.example.myapplication.coroutinetest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import kotlinx.android.synthetic.main.main_fragment.buttonLike
import kotlinx.android.synthetic.main.main_fragment.view.buttonLike
import kotlinx.android.synthetic.main.main_fragment.view.textViewLikeCount
import kotlinx.coroutines.Dispatchers.IO

class CoroutineTestFragment : Fragment() {

    companion object {
        fun newInstance() = CoroutineTestFragment()
    }

    private var myview: View? = null
    private lateinit var viewModel: CoroutineTestViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        myview = inflater.inflate(R.layout.main_fragment, container, false)
        myview!!.buttonLike.setOnClickListener {
            viewModel.addLikeCount()
        }
        return myview!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = CoroutineTestViewModel(IO)
        viewModel.likeCountLiveData.observe(
            viewLifecycleOwner,
            {
                myview!!.textViewLikeCount.text = it
            }
        )
    }
}
