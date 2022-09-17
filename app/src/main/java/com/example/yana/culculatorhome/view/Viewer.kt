package com.example.yana.culculatorhome.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.yana.culculatorhome.R
import com.example.yana.culculatorhome.controller.Controller

class Viewer : AppCompatActivity() {

    private var controller: Controller
    private var inputTv: TextView? = null
    private var resultTv: TextView? = null


    init {
        println("Start Viewer constructor")
        controller = Controller(viewer = this)
        println("I am Viewer object")
        println("End Viewer constructor")
    }

    companion object{
        const val EDITOR_SAVE_KEY = "EDITOR_SAVE_KEY"
        const val SAVE_KEY = "SAVE_KEY"

        const val WORKING_TEXT_SAVE_KEY = "WORKING_TEXT_SAVE_KEY"
        const val RESULT_SAVE_KEY = "RESULT_SAVE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputTv = findViewById(R.id.inputData)
        resultTv = findViewById(R.id.result)

        if (savedInstanceState != null){
            inputTv?.text = savedInstanceState.getString(WORKING_TEXT_SAVE_KEY)
            resultTv?.text = savedInstanceState.getString(RESULT_SAVE_KEY)
        }


        val btnOne = findViewById<Button>(R.id.btnOne)
        btnOne.setOnClickListener(controller)
        val btnTwo = findViewById<Button>(R.id.btnTwo)
        btnTwo.setOnClickListener(controller)
        val btnTree = findViewById<Button>(R.id.btnThree)
        btnTree.setOnClickListener(controller)
        val btnFour = findViewById<Button>(R.id.btnFour)
        btnFour.setOnClickListener(controller)
        val btnFive = findViewById<Button>(R.id.btnFive)
        btnFive.setOnClickListener(controller)
        val btnSix = findViewById<Button>(R.id.btnSix)
        btnSix.setOnClickListener(controller)
        val btnSeven = findViewById<Button>(R.id.btnSeven)
        btnSeven.setOnClickListener(controller)
        val btnEight = findViewById<Button>(R.id.btnEight)
        btnEight.setOnClickListener(controller)
        val btnNine = findViewById<Button>(R.id.btnNine)
        btnNine.setOnClickListener(controller)
        val btnZero = findViewById<Button>(R.id.btnZero)
        btnZero.setOnClickListener(controller)
        val btnPoint = findViewById<Button>(R.id.btnPoint)
        btnPoint.setOnClickListener(controller)

        val btnEqually = findViewById<Button>(R.id.btnEqually)
        btnEqually.setOnClickListener(controller)
        val btnPlus = findViewById<Button>(R.id.btnPlus)
        btnPlus.setOnClickListener(controller)
        val btnMines = findViewById<Button>(R.id.btnMines)
        btnMines.setOnClickListener(controller)
        val btnQuota = findViewById<Button>(R.id.btnQuota)
        btnQuota.setOnClickListener(controller)
        val btnPercent = findViewById<Button>(R.id.btnPercent)
        btnPercent.setOnClickListener(controller)
        val btnDivide = findViewById<Button>(R.id.btnDivide)
        btnDivide.setOnClickListener(controller)
        val btnMulti = findViewById<Button>(R.id.btnMulti)
        btnMulti.setOnClickListener(controller)
        val btnNext = findViewById<Button>(R.id.btnNext)
        btnNext.setOnClickListener(controller)

        val btnAc = findViewById<Button>(R.id.btnAc)
        btnAc.setOnClickListener {
            controller.clear()
            inputTv?.text = ""
            resultTv?.text = ""
        }

}
    fun update(temp: String) {
        val result = resultTv
        result?.text = temp
    }
}