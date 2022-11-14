package com.example.ui.fragmentQuestionList

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.Question
import com.example.quizapp.databinding.ItemQuestionBinding

class QuestionsAdapter(
    private val questions: ArrayList<Question>,
    private val onQuestionListener: OnQuestionListener
) :
    RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder>() {

    private lateinit var binding: ItemQuestionBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        binding = ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(binding, onQuestionListener)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = questions[position]
        holder.bind(question)
    }

    override fun getItemCount() = questions.size

    inner class QuestionViewHolder(
        private val binding: ItemQuestionBinding,
        val onQuestionListener: OnQuestionListener
    ) : RecyclerView.ViewHolder(binding.root), OnClickListener {
        fun bind(question: Question) {
            binding.questionItem = question
            binding.bDelete.setOnClickListener {
                onClick(it)
            }
        }

        override fun onClick(p0: View?) {
            onQuestionListener.onDeleteClick(adapterPosition)
        }
    }

    interface OnQuestionListener {
        fun onDeleteClick(position: Int)
    }
}