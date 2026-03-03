package array.task

import kotlin.math.*
import kotlin.random.Random

object ArrayTask {
    fun createP(): ShortArray = ShortArray(9){i -> (7 + i * 2).toShort()}

    fun createX(seed: Int = 1): DoubleArray {
        val random = Random(seed)
        return DoubleArray(18){random.nextDouble(-9.0, 11.0)}
    }

    private fun formula13(x: Double): Double {
        return try {
            val expX = exp(x)
            if (expX == 0.0) return 0.0

            val twoPiDivExpX = divide(2 * PI, expX)
            val tanX = tan(x)
            val cosTanX = cos(tanX)

            val powValue = pow(twoPiDivExpX, cosTanX)
            val expPow = exp(powValue)

            if (expPow == 0.0) 0.0
            else atan(divide(1.0,expPow))
        } catch (_: Exception) {
            0.0
        }

    }

    private fun formulaIn(x: Double): Double {
        return try{
            val cbrtX = cbrt(x)
            val atanValue = atan(divide(x + 1, 2 * E) + 1)
            val powValue = pow(divide(cbrtX, 2.0), atanValue)
            SafeMath.sin(powValue)
        }catch (_: Exception){
            0.0}
    }

    private fun formulaDefault(x: Double): Double {
        return try{
            if (x == 0.0) return 0.0

            val xPow = pow(x, x * (x + 1))
            val cosXPow = cos(xPow)

            val twoXPow3 = pow(2 * x, 3.0)

            val xDiv1MinusX = divide(x, 1 - x)
            val xDivPowX = pow(xDiv1MinusX, x)

            val innerPow = pow(twoXPow3, divide(xDivPowX, 2.0))

            val sum = innerPow + 2.0 / 3.0

            val absX = abs(x)
            val expNegAbsX = exp(-absX)
            val atanExp = atan(expNegAbsX)

            val outerPow = pow(cosXPow * sum, atanExp)

            SafeMath.sin(outerPow)
        }catch(_: Exception){
            0.0
        }
    }

    fun calculateQ(p: ShortArray, x: DoubleArray): Array<DoubleArray> {
        return Array(p.size){i ->
            DoubleArray(x.size) {j ->
                when {
                    p[i] == 13.toShort() -> formula13(x[j])
                    p[i] in setOf<Short>(7, 19, 21, 23) -> formulaIn(x[j])
                    else -> formulaDefault(x[j])
                }
            }
        }
    }

    fun printMatrix(q: Array<DoubleArray>){
        q.forEach { row ->
            row.forEach { value ->
                print("%.5f".format(value))
            }
            println()
        }
    }
}

fun main(){
    val p = ArrayTask.createP()
    val x = ArrayTask.createX()
    val q = ArrayTask.calculateQ(p, x)

    ArrayTask.printMatrix(q)
}