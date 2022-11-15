package com.example.data

object QuestionsRepository {

    fun getAllData(): MutableList<Question> {
        return arrayOfQuestions
    }



    fun getFiveRandomQuestions(): MutableList<Question> {
        val fiveRandomQuestions = arrayOfQuestions.asSequence().shuffled().take(5).toMutableList()
        return fiveRandomQuestions
    }

    private val arrayOfQuestions = mutableListOf(
        Question(
            text = "What is the size of float variable?",
            answers = mutableListOf(
                "8 bit",
                "16 bit",
                "32 bit",
                "64 bit"

            ),
            correct = 3
        ),
        Question(
            text = "What is the default value of short variable?",
            answers = mutableListOf(
                " 0.0",
                "0",
                "null",
                "undefined"
            ),
            correct = 2
        ),
        Question(
            text = "What is the default value of Boolean variable?",
            answers = mutableListOf(
                "true",
                "false",
                "null",
                "not defined",
            ),
            correct = 0
        ),
        Question(
            text = "What is instance variable?",
            answers = mutableListOf(
                "Instance variables are static variables within a class but outside any method.",
                "Instance variables are variables defined inside methods, constructors or blocks.",
                "Instance variables are variables within a class but outside any method.",
                "None of the above."
            ),
            correct = 3
        ),
        Question(
            text = "When static binding occurs?",
            answers = mutableListOf(
                "Static binding occurs during Compile time.",
                "Static binding occurs during load time.",
                "Static binding occurs during runtime.",
                "None of the above."
            ),
            correct = 3
        ),
        Question(
            text = "Which is the way in which a thread can enter the waiting state?",
            answers = mutableListOf(
                "Invoke its sleep() method.",
                "invoke object's wait method.",
                "Invoke its suspend() method.",
                "All of the above."
            ),
            correct = 3
        ),
        Question(
            text = "How do you get the length of a string in Kotlin?",
            answers = mutableListOf(
                "str.length",
                "length(str)",
                "str.lengthOf",
                "str.sizeOf"
            ),
            correct = 3
        ),
        Question(
            text = "Which of the followings constructors are available in Kotlin?",
            answers = mutableListOf(
                "Primary constructor",
                "Secondary constructor",
                "Both 1 & 2",
                "None of the above"
            ),
            correct = 2
        ),
        Question(
            text = "Which of the following extension methods are used in Kotlin?",
            answers = mutableListOf(
                "Read texts () & Headlines ()",
                "Buffer reader ()",
                "Read each line ()",
                "All of the above"
            ),
            correct = 1
        ),
        Question(
            text = "Which of the following is not the basic data types in Kotlin?",
            answers = mutableListOf(
                "Numbers",
                "Strings",
                "Arrays",
                "Lists"
            ),
            correct = 3
        )
    )
}