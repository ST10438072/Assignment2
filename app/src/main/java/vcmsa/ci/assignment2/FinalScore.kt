package vcmsa.ci.assignment2

import android.os.Bundle
import android.util.Log.v
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FinalScore : AppCompatActivity() {

    var scor:  TextView? = null
    var rest: TextView? = null
    var rev: TextView? = null
    var result: Button? = null
    var exit: Button? = null

    val Questions = arrayOf("Ragnar was a King","USA lost the war to Vietnam","The Twin Towers fell in 2002",
        "The French revolution began in 1787","The Nazi's exterminated Muslims in their camps")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)

        scor = findViewById<View>(R.id.resttext) as TextView
        rest = findViewById<View>(R.id.scoretext) as TextView
        result = findViewById<View>(R.id.revbutton) as Button
        exit = findViewById<View>(R.id.exitbutton) as Button
        rev = findViewById< View>(R.id.reviewtex) as TextView

        val score = intent.getIntExtra("score",0)
        val Question =intent.getStringArrayExtra("questions")
        val Answers = intent.getStringArrayExtra("answers")

        scor!!.text = "Your Score: $score / ${Question!!.size} "

        if (score < 3){
            rest!!.text = "Needs Improving"
        } else if (score < 5){
            rest!!.text = "Good Work"
        } else {
            rest!!.text = "Amazing"
        }
            result!!.setOnClickListener {
                val Questions = "${Questions[0]}- True\n${Questions[1]}- True\n${Questions[2]} - False\n" +
                        "${Questions[3]}- False\n${Questions[4]}- False"
                rev!!.text =Questions
            }
                exit!!.setOnClickListener {
                    finishAffinity()
                }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}