package com.example.quizapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestionViewModel : ViewModel() {
    private val mutableSelectedItem = MutableLiveData<Question>()
    val selectedItem: LiveData<Question> get() = mutableSelectedItem

    private val mutableQuestionList = MutableLiveData<List<Question>>()
    val questionList: LiveData<List<Question>> get() = mutableQuestionList

    private val mutableIndex = MutableLiveData<Int>()
    val index: LiveData<Int> get() = mutableIndex

    private val mutableScore = MutableLiveData<Int>()
    val score: LiveData<Int> get() = mutableScore


    //beallitjuk a kezdeti kerdes listat
    fun setQuestionList(questionList: List<Question>) {
        mutableQuestionList.value = questionList
    }

    //beallitjuk az uj kivalasztott kerdest
    fun setNextQuestion() {
        mutableIndex.value= mutableIndex.value?.plus(1)
        mutableSelectedItem.value = questionList.value?.get(mutableIndex.value!!)
    }

    fun incrementScore() {
        mutableScore.value= mutableScore.value?.plus(1)
    }

    fun setFirstQuestion() {
        mutableScore.value = 0;
        mutableIndex.value = 0
        mutableSelectedItem.value = mutableQuestionList.value?.get(0)
    }


}