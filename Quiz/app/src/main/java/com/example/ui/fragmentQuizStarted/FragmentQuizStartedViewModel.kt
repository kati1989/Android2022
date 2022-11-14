package com.example.ui.fragmentQuizStarted

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.Question
import com.example.data.QuestionsRepository

class FragmentQuizStartedViewModel : ViewModel() {

    val questionTitle = MutableLiveData("")
    val idRbChecked = MutableLiveData(0)
    val totalScore = MutableLiveData(0)
    val fiveRandomQuestions = MutableLiveData<MutableList<Question>>()
    val firstQuestionInList = MutableLiveData<Question>()

    init {
        generateFiveRandomQuestions()
        firstQuestionInList.value = fiveRandomQuestions.value?.get(0)
        fiveRandomQuestions.value?.removeAt(0)
    }

    private fun generateFiveRandomQuestions() {
        fiveRandomQuestions.value = QuestionsRepository.getFiveRandomQuestions()
    }

    fun removeFirstQuestionFromListAndChangeFirstQuestion() {
        if (fiveRandomQuestions.value?.isNotEmpty() == true) {
            fiveRandomQuestions.value?.removeAt(0)
            if (fiveRandomQuestions.value?.isNotEmpty() == true) {
                firstQuestionInList.value = fiveRandomQuestions.value?.get(0)
            }
        }
    }
}