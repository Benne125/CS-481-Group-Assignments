package com.cs481.assignment_2

import android.widget.Button
import android.widget.TextView
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var scoreInput: EditText
    private lateinit var showGradeButton: Button
    private lateinit var gradeResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        scoreInput = findViewById(R.id.scoreInput)
        showGradeButton = findViewById(R.id.showGradeButton)
        gradeResult = findViewById(R.id.gradeResult)

        showGradeButton.setOnClickListener { showGrade() }
    }
    private fun showGrade() {
        val text = scoreInput.text.toString().trim()

        //Empty input
        if (text.isEmpty()) {
            gradeResult.text = getString(R.string.empty_score)
            return
        }
        //Not a valid number
        val score = text.toDoubleOrNull()
        if (score == null || score.isNaN() || score.isInfinite()) {
            gradeResult.text = getString(R.string.invalid_Number)
            return
        }
        //Out of range
        if (score < 0.0 || score > 100.0) {
            gradeResult.text = getString(R.string.invalid_Range)
            return
        }
        val gradeText = getString(letterGradeResId(score))
        gradeResult.text = getString(R.string.grade_result, gradeText)
    }
    private fun letterGradeResId(score: Double): Int = when {
        score < 70.0 -> R.string.grade_F
        score < 73.0 -> R.string.grade_C_minus
        score < 77.0 -> R.string.grade_C
        score < 80.0 -> R.string.grade_C_plus
        score < 83.0 -> R.string.grade_B_minus
        score < 87.0 -> R.string.grade_B
        score < 90.0 -> R.string.grade_B_plus
        score < 93.0 -> R.string.grade_A_minus
        score < 97.0 -> R.string.grade_A
        else -> R.string.grade_A
    }
}