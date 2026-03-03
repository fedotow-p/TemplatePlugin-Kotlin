package array.task

import kotlin.math.*

object SafeMath {
    fun sin(value: Double): Double {
        return try {
            if (value.isFinite()) kotlin.math.sin(value) else 0.0
        } catch (_: Exception) {
            0.0
        }
    }
}

fun cos(value: Double): Double {
    return try {
        if (value.isFinite()) kotlin.math.cos(value) else 1.0
    } catch (_: Exception) {
        1.0
    }
}

fun tan(value: Double): Double {
    return try {
        if (value.isFinite() && kotlin.math.tan(value % PI) != PI / 2) {
            kotlin.math.tan(value)
        } else {
            0.0
        }
    } catch (_: Exception){
        0.0
    }
}

fun atan(value: Double): Double {
    return try {
        if (value.isFinite()) kotlin.math.atan(value) else 0.0
    } catch (_: Exception) {
        0.0
    }
}

fun exp(value: Double): Double {
    return try {
        when {
            value > 700 -> Double.POSITIVE_INFINITY
            value < -700 -> 0.0
            else -> kotlin.math.exp(value)
        }
    } catch (_: Exception) {
        0.0
    }
}

fun pow(base: Double, exponent: Double): Double {
    return try {
        when {
            base < 0 && exponent % 1 != 0.0 -> {
                0.0
            }
            base == 0.0 && exponent < 0 -> {
                Double.POSITIVE_INFINITY
            }
            base == 0.0 && exponent == 0.0 -> {
                1.0
            }
            else -> {
                val result = base.pow(exponent)
                if (result.isFinite()) result else 0.0
            }
        }
    } catch (_: Exception) {
        0.0
    }
}

fun cbrt(value: Double): Double {
    return try {
        if (value.isFinite()) kotlin.math.cbrt(value) else 0.0
    } catch (_: Exception) {
        0.0
    }
}

fun abs(value: Double): Double {
    return try {
        if (value.isFinite()) kotlin.math.abs(value) else 0.0
    } catch (_: Exception) {
        0.0
    }
}

fun divide(numerator: Double, denominator: Double, defaultValue: Double = 0.0): Double {
    return try {
        if (denominator == 0.0 || denominator.isNaN() || !denominator.isFinite()) {
            defaultValue
        } else {
            numerator / denominator
        }
    } catch (_: Exception) {
        defaultValue
    }
}
