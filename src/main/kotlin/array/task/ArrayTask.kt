package array.task

import kotlin.math.*
import kotlin.random.Random

object ArrayTask {
    fun createP(): ShortArray = ShortArray(9){i -> (7 + i * 2).toShort()}

    fun createX(seed: Int = 1): DoubleArray {
        val random = Random(seed)
        return DoubleArray(18){random.nextDouble(-9.0, 11.0)}
    }

    private fun formula13(x: Double): Double = atan(1.0 / exp((2 * PI / exp(x)).pow(cos(tan(x)))))

    private fun formulaIn(x: Double): Double = sin((cbrt(x) / 2.0).pow(atan((x+1) / (2 * E) + 1)))

    private fun formulaDefault(x: Double): Double = sin((cos(x.pow(x*(x + 1))) * ((2 * x).pow(3.0).pow((x/ (1 - x)).pow(x) / 2.0) +2.0 / 3.0)).pow(atan(exp(-abs(x)))))

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