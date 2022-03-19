package com.zybooks.pizzaparty

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


/**
 * The Main Activity of the Pizza Party App
 *
 * This class lets you calculate how many pizzas you will need
 */

const val TAG = "MainActivity"
private const val KEY_TOTAL_PIZZAS = "totalPizzas"

class MainActivity : AppCompatActivity() {

    private var totalPizzas = 0

    private lateinit var numAttendEditText: EditText
    private lateinit var numPizzasTextView: TextView
    private lateinit var howHungryRadioGroup: RadioGroup

    /**
     * Called after Activity is created
     * @param savedInstanceState the app bundle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate was called")

        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)

        if (savedInstanceState != null) {
            totalPizzas = savedInstanceState.getInt(KEY_TOTAL_PIZZAS)
            displayTotal()
        }

        // Not sure what I am supposed to do here. The assignment says:
        //
        // "Add a TextWatcher to Pizza Party's numAttendEditText.
        // The TextWatcher should set numAttendEditText's text to
        // an empty string as soon as the user starts typing the number of people."
        //
        // If I set the text to an empty string as soon as they start typing
        // how will they be able to enter a value?
        numAttendEditText.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_TOTAL_PIZZAS, totalPizzas)
    }

    /**
     * Called when calculate button is clicked
     * @param view the view
     */
    fun calculateClick(view: View) {

        // Get the text that was typed into the EditText
        val numAttendStr = numAttendEditText.text.toString()

        // Convert the text into an integer
        val numAttend = numAttendStr.toIntOrNull() ?: 0

        // Get hunger level selection
        val hungerLevel = when (howHungryRadioGroup.getCheckedRadioButtonId()) {
            R.id.light_radio_button -> PizzaCalculator.HungerLevel.LIGHT
            R.id.medium_radio_button -> PizzaCalculator.HungerLevel.MEDIUM
            else -> PizzaCalculator.HungerLevel.RAVENOUS
        }

        // Get the number of pizzas needed
        val calc = PizzaCalculator(numAttend, hungerLevel)
        totalPizzas = calc.totalPizzas
        displayTotal()
    }

    private fun displayTotal() {
        val totalText = getString(R.string.total_pizzas, totalPizzas)
        numPizzasTextView.text = totalText
    }
}