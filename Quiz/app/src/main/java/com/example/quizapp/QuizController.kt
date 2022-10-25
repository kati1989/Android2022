package com.example.quizapp;

import android.content.res.Resources
import java.io.File
import java.util.*
import android.content.res.AssetManager




class QuizController {
    var quizList: MutableList<Question> = mutableListOf()

    constructor(assets: AssetManager) {

        val lineList = mutableListOf<String>()
        assets.open("quiz.txt").bufferedReader().use{
            it.readText()
        }.split("\n").forEach { item -> lineList.add(item) }

        for (i in 0..lineList.size -1 step 6){
            var q: Question = Question(lineList.get(i), listOf(lineList.get(i+1),lineList.get(i+2),lineList.get(i+3),lineList.get(i+4)),lineList.get(i+5));
            quizList.add(q)
        }
    }

    fun randomizeQuestions() {
        quizList.shuffle();
    }
}