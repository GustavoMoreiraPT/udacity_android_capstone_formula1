package com.udacity.capstone.formula1.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.capstone.formula1.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this


        binding.buttonDrivers.setOnClickListener {
            this.findNavController().navigate(MainFragmentDirections.actionMainFragmentToMainDriverFragment())
        }

        binding.buttonConstructors.setOnClickListener {
            this.findNavController().navigate(MainFragmentDirections.actionMainFragmentToMainConstructorFragment())
        }

        return binding.root
    }
}