package vcmsa.ci.assignment2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Question1 : AppCompatActivity() {

    var question: TextView? = null
    var answer: TextView? = null
    var show: Button? = null
    var questions: RadioGroup? = null

    var score = 0

    val Questions = arrayOf("Ragnar was a King","USA lost the war to Vietnam","The Twin Towers fell in 2002",
        "The French revolution began in 1787","The Nazi's exterminated Muslims in their camps")
    val Choice = arrayOf(arrayOf("True","False"),arrayOf("True","False"),arrayOf("True","False"),
        arrayOf("True","False"),arrayOf("True","False"))

    var Answers = arrayOf(0,0,1,1,1)

    var QuestionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question1)

        question = findViewById<View>(R.id.textView3) as TextView
        answer = findViewById<View>(R.id.resulttext) as TextView
        show = findViewById<View>(R.id.showbutton) as Button
        questions = findViewById<View>(R.id.question1) as RadioGroup

        QuestionView()

        show!!.setOnClickListener {
            val selectedID = questions!!.checkedRadioButtonId
            if(selectedID != -1){

                val selectedRadioID = findViewById<RadioButton>(selectedID)
                val selectedAnswerIndex = questions!!.indexOfChild(selectedRadioID)

                if (selectedAnswerIndex == Answers[QuestionIndex]){
                    score++
                    answer!!.text = "Correct"
                }
                else{ answer!!.text = "Incorrect"
                }
                QuestionIndex++
            }
            if (QuestionIndex < Questions.size){
                QuestionView()
            } else {
                val intent = Intent(this, FinalScore::class.java)
                intent.putExtra("score", score)
                intent.putExtra("questions", Questions)
                intent.putExtra("answers", Answers)
                startActivity(intent)
                finish()
            }

        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun QuestionView(){
        question!!.text = Questions[QuestionIndex]
        questions!!.removeAllViews()

        for(choice in Choice[QuestionIndex]){
            var radioButton = RadioButton(this)
            radioButton.text = choice
            questions!!.addView(radioButton)
        }
    }
}