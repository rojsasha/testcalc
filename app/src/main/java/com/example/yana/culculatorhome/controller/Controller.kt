package com.example.yana.culculatorhome.controller

import android.view.View
import com.example.yana.culculatorhome.R
import com.example.yana.culculatorhome.model.Model
import com.example.yana.culculatorhome.view.Viewer

class Controller() : View.OnClickListener {

    private lateinit var model: Model

    constructor(viewer: Viewer) : this() {
        model = Model(viewer = viewer)
    }

    fun clear() = model.clear()
    fun backAction() = model.backAction()
    fun resultsCalc() = model.resultsCalc()

    override fun onClick(v: View?) {
        var number = ""
        val itemId = v?.id
        when(itemId){
            R.id.btnOne -> number = "One"
            R.id.btnTwo -> number = "Two"
            R.id.btnPlus -> number = "Plus"
            R.id.btnEqually -> number = "Equal"
            R.id.btnThree -> number = "Three"
            R.id.btnFour -> number = "Four"
            R.id.btnFive -> number = "Five"
            R.id.btnSix -> number = "Six"
            R.id.btnSeven -> number = "Seven"
            R.id.btnEight -> number = "Eight"
            R.id.btnNine -> number = "Nine"
            R.id.btnZero -> number = "Zero"
            R.id.btnPoint -> number = "Point"
            R.id.btnMines -> number = "Mines"
            R.id.btnMulti -> number = "Multi"
            R.id.btnDivide -> number = "Divide"
            R.id.btnAc -> number = "AC"
            R.id.btnNext -> number = "Next"

        }

      model.action(number)
    }
}