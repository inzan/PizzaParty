package com.zybooks.pizzaparty

import kotlin.math.ceil

const val SLICES_PER_PIZZA = 8

/**
 * The Main Activity of the Pizza Party App
 *
 * This class lets you calculate how many pizzas you will need
 */

class PizzaCalculator(partySize: Int, var hungerLevel: HungerLevel) {

    /**
     * stores how many people are in the party
     */
    var partySize = 0
        set(value) {
            field = if (value >= 0) value else 0
        }

    /**
     * enum used to determine how hungry people are
     */
    enum class HungerLevel(var numSlices: Int) {
        LIGHT(2), MEDIUM(3), RAVENOUS(4)
    }

    /**
     * calculates the total number of pizzas
     */
    val totalPizzas: Int
        get() {
            return ceil(partySize * hungerLevel.numSlices / SLICES_PER_PIZZA.toDouble()).toInt()
        }

    /**
     * initialize properties
     */
    init {
        this.partySize = partySize
    }
}