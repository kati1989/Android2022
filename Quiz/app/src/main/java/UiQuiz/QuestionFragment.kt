package UiQuiz

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import com.example.quizapp.Question
import com.example.quizapp.QuestionViewModel
import com.example.quizapp.R
import androidx.activity.OnBackPressedCallback

import androidx.annotation.NonNull





private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var questionView: TextView
    private lateinit var answer1: RadioButton
    private lateinit var answer2: RadioButton
    private lateinit var answer3: RadioButton
    private lateinit var answer4: RadioButton
    private lateinit var nextButton: Button
    private lateinit var radioGroup: RadioGroup

    val viewModel: QuestionViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        view?.apply {
            questionView = findViewById(R.id.questionView)
            answer1 = findViewById(R.id.answer1)
            answer2 = findViewById(R.id.answer2)
            answer3 = findViewById(R.id.answer3)
            answer4 = findViewById(R.id.answer4)
            nextButton = findViewById(R.id.next)
            radioGroup = findViewById(R.id.answerGroup)
        }

        viewModel.setFirstQuestion();
        initializeView();

        nextButton.setOnClickListener {
            if (viewModel.index.value!! < viewModel.questionList.value!!.size - 1) {
                // get selected radio button from radioGroup
                val selectedId: Int = radioGroup.checkedRadioButtonId
                val userAnswer = setOf(answer1,answer2,answer3,answer4).find { it.id == selectedId }!!.text
                if (userAnswer.equals(viewModel.selectedItem.value!!.correct)){
                    viewModel.incrementScore()
                }

                if (viewModel.index.value!! == viewModel.questionList.value!!.size - 2){
                    nextButton.text = "Submit"
                } else {
                }
                viewModel.setNextQuestion()
                initializeView();
            } else {
                val fm: FragmentManager = parentFragmentManager
                val ft: FragmentTransaction = fm.beginTransaction()
                ft.replace(R.id.fragment_placeHolder, QuizEndFragment())
                ft.commit()
            }
        }

        return view
    }

    private fun initializeView() {
        val actualQuestion: Question = viewModel.selectedItem.value!!

        questionView.text = actualQuestion.text
        answer1.text = actualQuestion.answers.get(0)
        answer2.text = actualQuestion.answers.get(1)
        answer3.text = actualQuestion.answers.get(2)
        answer4.text = actualQuestion.answers.get(3)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                val builder = AlertDialog.Builder(context)
                builder.setMessage("Are you sure you want to End this Quiz?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->
                        val fm: FragmentManager = parentFragmentManager
                        val ft: FragmentTransaction = fm.beginTransaction()
                        ft.replace(R.id.fragment_placeHolder, QuizEndFragment())
                        ft.commit()
                    }
                    .setNegativeButton("No") { dialog, id ->
                        // Dismiss the dialog
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,  // LifecycleOwner
            callback
        )
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuestionFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}