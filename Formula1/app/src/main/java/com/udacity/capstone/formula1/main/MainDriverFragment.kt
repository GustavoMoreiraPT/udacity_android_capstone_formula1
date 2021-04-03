package com.udacity.capstone.formula1.main

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.capstone.formula1.database.F1Database
import com.udacity.capstone.formula1.databinding.FragmentMainConstructorBinding
import com.udacity.capstone.formula1.databinding.FragmentMainDriverBinding
import com.udacity.capstone.formula1.dependencyinjection.Injection

class MainDriverFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainDriverBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val application = requireNotNull(activity).application

        val di = Injection()

        F1Database.getInstance(application)

        val viewModelFactory = MainViewModelFactory(di.repository, application)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        mainViewModel = viewModel

        binding.viewModel = viewModel

        val manager = LinearLayoutManager(context)
        binding.driverRecycler.layoutManager = manager

        val adapter = DriverAdapter(DriverListener { driver ->
            viewModel.onDriverClicked(driver)
        })

        viewModel.navigateToDriverDetail.observe(viewLifecycleOwner, Observer {
                driver -> driver?.let {
                    this.findNavController().navigate(MainDriverFragmentDirections.actionMainDriverFragmentToDriverDetailFragment(driver))
                    viewModel.onDriverNavigated()
                }
        })

        binding.driverRecycler.adapter = adapter

        viewModel.drivers.observe(viewLifecycleOwner, Observer {
            it?.let { adapter.addHeaderAndSubmitList(it) }
        })

        setHasOptionsMenu(true)

        return binding.root
    }
}