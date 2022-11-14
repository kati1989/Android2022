package com.example.ui.fragmentQuestionList

import androidx.lifecycle.ViewModel
import com.example.data.Question
import com.example.data.QuestionsRepository

class FragmentQuestionListViewModel : ViewModel(), QuestionsAdapter.OnQuestionListener {

    val questions = ArrayList<Question>()
    val questionsAdapter = QuestionsAdapter(questions = questions, this)

    init {
        loadQuestions()
    }

    override fun onDeleteClick(position: Int) {
        questions.removeAt(position) // deleteQuestionFromIndex
        questionsAdapter.notifyDataSetChanged()
    }

    private fun loadQuestions() {
        questions.clear()
        questions.addAll(QuestionsRepository.getAllData())
        questionsAdapter.notifyDataSetChanged()
    }
}