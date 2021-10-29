package com.example.yana.culculatorhome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yana.culculatorhome.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOne.setOnClickListener { pokaz("1", true) }
        binding.btnTwo.setOnClickListener { pokaz("2", true) }
        binding.btnThree.setOnClickListener { pokaz("3", true) }
        binding.btnFour.setOnClickListener { pokaz("4", true) }
        binding.btnFive.setOnClickListener { pokaz("5", true) }
        binding.btnSix.setOnClickListener { pokaz("6", true) }
        binding.btnSeven.setOnClickListener { pokaz("7", true) }
        binding.btnEight.setOnClickListener { pokaz("8", true) }
        binding.btnNine.setOnClickListener { pokaz("9", true) }
        binding.btnZero.setOnClickListener { pokaz("0", true) }
        binding.btnPoint.setOnClickListener { pokaz(".", true) }

        binding.btnEqually.setOnClickListener { pokaz("=" , true) }
        binding.btnPlus.setOnClickListener { pokaz("+", true) }
        binding.btnMines.setOnClickListener { pokaz("-", true) }
        binding.btnQuota.setOnClickListener { pokaz("( )", true) }
        binding.btnPercent.setOnClickListener { pokaz("%", true) }
        binding.btnDivide.setOnClickListener { pokaz("/", true) }
        binding.btnMulti.setOnClickListener { pokaz("*", true) }
        binding.btnAc.setOnClickListener { pokaz("AC", true) }

    }

    fun pokaz(stroka: String, ochistka: Boolean){
        if (binding.btnEqually.text.isNotEmpty()){
            binding.result.text = ""
        }
        if (ochistka){
            binding.btnEqually.text = ""
            binding.result.append(stroka)
        }
        else{
            binding.result.append(binding.btnEqually.text)
            binding.result.append(stroka)
            binding.btnEqually.text = ""
        }
    }
}