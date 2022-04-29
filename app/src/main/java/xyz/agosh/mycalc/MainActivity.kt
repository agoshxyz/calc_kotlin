package xyz.agosh.mycalc

import java.util.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    var lastNumeric: Boolean = false;
    var lastDot: Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        inputTextView.append((view as Button).text)
        lastNumeric = true;

        if (inputTextView.text.length == 12) {
            Toast.makeText(this, "Maximum limit is 12 digits", Toast.LENGTH_SHORT).show()
        }
        //inputTextView.append("hello world")
        //Toast.makeText(this, "button has been pressed", Toast.LENGTH_SHORT).show()
    }

    fun onClear(view: View) {
        inputTextView.text = ""
        lastNumeric = false;
        lastDot = false;
        //Toast.makeText(this, "console has been cleared", Toast.LENGTH_SHORT).show()
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            inputTextView.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onRemove(view: View) {
        var value = inputTextView.text
        if (lastNumeric) {
            value = value.substring(0, value.length - 1);
            inputTextView.setText(value.toString())
        }
    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            var textViewValue = inputTextView.text.toString()

            var prefix = ""

            try {

                if (textViewValue.startsWith("-")) {
                    prefix = "-"
                    textViewValue = textViewValue.substring(1)
                    // -215
                }

                //Substraction
                if (textViewValue.contains("-")) {
                    val splitValue = textViewValue.split("-")
                    //99-1
                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    inputTextView.text =
                        removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())

                }

                //Adding
                if (textViewValue.contains("+")) {
                    val splitValue = textViewValue.split("+")
                    //99-1
                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    inputTextView.text =
                        removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                }

                //Division
                if (textViewValue.contains("/")) {
                    val splitValue = textViewValue.split("/")
                    //99-1
                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    inputTextView.text =
                        removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())

                }

                //Multiplication
                if (textViewValue.contains("*")) {
                    val splitValue = textViewValue.split("*")
                    //99-1
                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    inputTextView.text =
                        removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())

                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String): String {
        var value = result;
        if (result.contains(".0")) {
            //99.0 ->   9 9 . 0
            // indexes: 0 1 2 3
            value = result.substring(0, result.length - 2)
        }
        return value;
    }


    fun onOperator(view: View) {
        if (lastNumeric && !isOperatorAdded(inputTextView.text.toString())) {
            inputTextView.append((view as Button).text)
            lastNumeric = false;
            lastDot = false;
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}