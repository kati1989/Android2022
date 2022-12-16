package com.treetrack.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.treetrack.api.data.activities.ActivitiesResponseItem
import com.treetrack.databinding.ItemActivitiesBinding

class ExampleAdapter(
    private val examples: ArrayList<ActivitiesResponseItem>
) : RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {

    private lateinit var binding: ItemActivitiesBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        binding = ItemActivitiesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExampleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val example = examples[position]
        holder.bind(example)
    }

    override fun getItemCount(): Int = examples.size

    inner class ExampleViewHolder(
        private val binding: ItemActivitiesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(example: ActivitiesResponseItem) {
            binding.example = example
        }
    }
}