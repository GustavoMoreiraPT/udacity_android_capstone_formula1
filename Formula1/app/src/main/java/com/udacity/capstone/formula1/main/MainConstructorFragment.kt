package com.udacity.capstone.formula1.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.udacity.capstone.formula1.databinding.FragmentMainConstructorBinding

class MainConstructorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainConstructorBinding.inflate(inflater)
        binding.lifecycleOwner = this

        setHasOptionsMenu(true)

        return binding.root
    }
}