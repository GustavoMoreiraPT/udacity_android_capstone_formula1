package com.udacity.capstone.formula1.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.capstone.formula1.R
import com.udacity.capstone.formula1.databinding.ListItemDriverBinding
import com.udacity.capstone.formula1.dto.Driver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1

class DriverAdapter(val clickListener: DriverListener) : ListAdapter<DataItemDriver,
        RecyclerView.ViewHolder>(DriverDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: List<Driver>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(DataItemDriver.Header)
                else -> listOf(DataItemDriver.Header) + list.map { DataItemDriver.DriverItemDriver(it) }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val driverItem = getItem(position) as DataItemDriver.DriverItemDriver
                holder.bind(clickListener, driverItem.driver)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItemDriver.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItemDriver.DriverItemDriver -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class TextViewHolder(view: View): RecyclerView.ViewHolder(view){
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.header, parent, false)
                return TextViewHolder(view)
            }
        }
    }

    class ViewHolder private constructor(private val binding: ListItemDriverBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: DriverListener, item: Driver) {
            binding.driver = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemDriverBinding.inflate(layoutInflater, parent,  false)

                return ViewHolder(binding)
            }
        }
    }
}

class DriverDiffCallback: DiffUtil.ItemCallback<DataItemDriver>() {
    override fun areItemsTheSame(oldItem: DataItemDriver, newItem: DataItemDriver): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItemDriver, newItem: DataItemDriver): Boolean {
        return oldItem == newItem
    }

}

class DriverListener(val clickListener: (driver: Driver) -> Unit) {
    fun onClick(driver: Driver) = clickListener(driver)
}

sealed class DataItemDriver {
    data class DriverItemDriver(val driver: Driver): DataItemDriver(){
        override val id = driver.id
    }

    object Header: DataItemDriver() {
        override val id = Long.MIN_VALUE
    }

    abstract val id: Long
}