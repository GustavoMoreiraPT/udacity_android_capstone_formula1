package com.udacity.capstone.formula1.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.udacity.capstone.formula1.databinding.FragmentDetailDriverBinding

class DriverDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailDriverBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }
}