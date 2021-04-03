package com.udacity.capstone.formula1.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.udacity.capstone.formula1.R

class AboutFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        coordinateMotion()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    private fun coordinateMotion() {
        val appBarLayout: AppBarLayout = this.requireView().findViewById(R.id.appbar_layout)
        val motionLayout: MotionLayout = this.requireView().findViewById(R.id.motion_layout)

        val listener = AppBarLayout.OnOffsetChangedListener { unused, verticalOffset ->
            val seekPosition = -verticalOffset / appBarLayout.totalScrollRange.toFloat()
            motionLayout.progress = seekPosition
        }

        appBarLayout.addOnOffsetChangedListener(listener)
    }
}