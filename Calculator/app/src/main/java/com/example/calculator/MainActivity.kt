package com.example.calculator

import android.os.Bundle
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private lateinit var history: TextView
    private var currentExpression: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator) // ✅ matches your XML file name

        // References
        display = findViewById(R.id.display)
        history = findViewById(R.id.history)

        // Digits and operators
        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
            R.id.btn8, R.id.btn9, R.id.btnDot,
            R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDiv,
            R.id.btnPercent, R.id.btnBrackets
        )

        for (id in buttons) {
            findViewById<MaterialButton>(id).setOnClickListener {
                val text = (it as MaterialButton).text.toString()
                currentExpression += text
                display.text = currentExpression
            }
        }

        // Clear button
        findViewById<MaterialButton>(R.id.btnC).setOnClickListener {
            currentExpression = ""
            display.text = "0"
            history.text = ""
        }

        // Plus/Minus toggle
        findViewById<MaterialButton>(R.id.btnPlusMinus).setOnClickListener {
            if (currentExpression.isNotEmpty()) {
                if (currentExpression.startsWith("-")) {
                    currentExpression = currentExpression.substring(1)
                } else {
                    currentExpression = "-$currentExpression"
                }
                display.text = currentExpression
            }
        }

        // Equals button
        findViewById<MaterialButton>(R.id.btnEqual).setOnClickListener {
            try {
                val result = eval(currentExpression)
                history.text = currentExpression
                display.text = result.toString()
                currentExpression = result.toString()
            } catch (e: Exception) {
                display.text = "Error"
                currentExpression = ""
            }
        }
    }

    // Simple expression evaluator
    private fun eval(expr: String): Double {
        return object {
            var pos = -1
            var ch = 0

            fun nextChar() { ch = if (++pos < expr.length) expr[pos].code else -1 }
            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.code) nextChar()
                if (ch == charToEat) { nextChar(); return true }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < expr.length) throw RuntimeException("Unexpected: " + ch.toChar())
                return x
            }

            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    when {
                        eat('+'.code) -> x += parseTerm()
                        eat('-'.code) -> x -= parseTerm()
                        else -> return x
                    }
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    when {
                        eat('×'.code) || eat('*'.code) -> x *= parseFactor()
                        eat('÷'.code) || eat('/'.code) -> x /= parseFactor()
                        else -> return x
                    }
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.code)) return parseFactor()
                if (eat('-'.code)) return -parseFactor()

                var x: Double
                val startPos = pos
                if (eat('('.code)) {
                    x = parseExpression()
                    eat(')'.code)
                } else if (ch in '0'.code..'9'.code || ch == '.'.code) {
                    while (ch in '0'.code..'9'.code || ch == '.'.code) nextChar()
                    x = expr.substring(startPos, pos).toDouble()
                } else {
                    throw RuntimeException("Unexpected: " + ch.toChar())
                }
                return x
            }
        }.parse()
    }
}
