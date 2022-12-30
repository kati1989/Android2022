package com.treetrack.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.treetrack.api.data.activities.ActivitiesResponseItem
import com.treetrack.databinding.ItemActivityBinding

class ActivitiesAdapter(
    private val activities: ArrayList<ActivitiesResponseItem>
) : RecyclerView.Adapter<ActivitiesAdapter.ActivityViewHolder>() {

    private lateinit var binding: ItemActivityBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        binding = ItemActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActivityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val activity = activities[position]
        holder.bind(activity)
    }

    override fun getItemCount(): Int = activities.size

    inner class ActivityViewHolder(
        private val binding: ItemActivityBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(activity: ActivitiesResponseItem) {
            binding.activity = activity
        }
    }
}