package com.example.part2_chap2_lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import java.util.Random

class MainActivity : AppCompatActivity() {

    private val numberPicker: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker)
    }

    private val addButton: Button by lazy {
        findViewById<Button>(R.id.addButton)
    }

    private val clearButton: Button by lazy {
        findViewById<Button>(R.id.clearButton)
    }

    private val runButton: Button by lazy {
        findViewById<Button>(R.id.runButton)
    }

    private val numberTextViewList: List<TextView> by lazy {
        listOf(
            findViewById(R.id.TextViewNum1),
            findViewById(R.id.TextViewNum2),
            findViewById(R.id.TextViewNum3),
            findViewById(R.id.TextViewNum4),
            findViewById(R.id.TextViewNum5),
            findViewById(R.id.TextViewNum6)
        )
    }

    private var didRun = false

    private val pickNumberSet = hashSetOf<Int>()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            numberPicker.minValue = 1
            numberPicker.maxValue = 45

            initAddButton()
            initClearButton()
            initRunButton()
        }

    private fun initAddButton() {
        addButton.setOnClickListener {
            if (didRun) {
                Toast.makeText(this, "초기화 후에 시도", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pickNumberSet.size >= 5) {
                Toast.makeText(this, "번호는 5개까지만 선택", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pickNumberSet.contains(numberPicker.value)) {
                Toast.makeText(this, "이미 선택한 번호", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val textView = numberTextViewList[pickNumberSet.size]
            textView.isVisible = true
            textView.text = numberPicker.value.toString()

            setNumberBackground(numberPicker.value, textView)

            pickNumberSet.add(numberPicker.value)
        }
    }

    private fun initClearButton() {
        clearButton.setOnClickListener {
            didRun = false
            numberTextViewList.forEach {
                it.isVisible = false
            }
            pickNumberSet.clear()
        }
    }

    private fun initRunButton() {
        runButton.setOnClickListener {
            val list = getRandomNumber()

            didRun = true

            list.forEachIndexed { index, number ->
                val textView = numberTextViewList[index]
                textView.text = number.toString()
                textView.isVisible = true

                setNumberBackground(number, textView)
            }

        }
    }

    private fun getRandomNumber(): List<Int> {
        val numberList = mutableListOf<Int>().apply {
            for (i in 1..45) {
                if (pickNumberSet.contains(i))
                    continue
                this.add(i)
            }
        }
        numberList.shuffle()

        val resultList = pickNumberSet.toList() + numberList.subList(0, 6 - pickNumberSet.size)

        return resultList.sorted()
    }

    private fun setNumberBackground(number:Int, textView: TextView) {
        when (number) {
            in 1..10 -> textView.background = ContextCompat.getDrawable(this, R.drawable.ball_yellow)
            in 11..20 -> textView.background = ContextCompat.getDrawable(this, R.drawable.ball_blue)
            in 21..30 -> textView.background = ContextCompat.getDrawable(this, R.drawable.ball_red)
            in 31..40 -> textView.background = ContextCompat.getDrawable(this, R.drawable.ball_gray)
            else -> textView.background = ContextCompat.getDrawable(this, R.drawable.ball_green)
        }
    }
}