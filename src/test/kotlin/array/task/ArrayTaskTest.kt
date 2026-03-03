package array.task

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.Test

class ArrayTaskTest {
    @Test
    fun testCreatePReturnsCorrectArray() {
        val p = ArrayTask.createP()

        assertEquals(9, p.size)

        val expectedValues = listOf<Short>(7, 9, 11, 13, 15, 17, 19, 21, 23)

        for (i in expectedValues.indices) {
            assertEquals(expectedValues[i], p[i])
        }
    }

    @Test
    fun testCreateXReturnsDeterministicArraysWithSameSeed() {
        val seed = 42
        val x1 = ArrayTask.createX(seed)
        val x2 = ArrayTask.createX(seed)

        assertEquals(18, x1.size)

        assertArrayEquals(x1, x2, 1e-10)
    }

    @Test
    fun testCreateXReturnsDifferentArraysWithDifferentSeed() {
        val x1 = ArrayTask.createX(42)
        val x2 = ArrayTask.createX(69)

        var isDifferent = false
        for (i in x1.indices) {
            if (abs(x2[i] - x1[i]) > 1e-10){
                isDifferent = true
                break
            }
        }
        assertTrue(isDifferent)
    }

    @Test
    fun testCreateXValuesAreWithinRange() {
        val x = ArrayTask.createX()

        for (value in x){
            assertTrue(value >= -9.0 && value <= 11.0)
        }
    }

    @Test
    fun testCalculateQForFormula13(){
        val p = shortArrayOf(13)
        val x = doubleArrayOf(0.0, 1.0, -1.0, 2.0)

        val q = ArrayTask.calculateQ(p, x)

        assertEquals(1, q.size)
        assertEquals(x.size, q[0].size)

        for (value in q[0]){
            assertFalse(value.isNaN())
            assertTrue(value.isFinite())
        }
    }

    @Test
    fun testCalculateQForFormulaIn(){
        val values = setOf<Short>(7, 19, 21, 23)

        for (pValue in values){
            val p = shortArrayOf(pValue)
            val x = doubleArrayOf(0.0, 1.0, -1.0, 2.0)

            val q = ArrayTask.calculateQ(p, x)

            assertDoesNotThrow{ArrayTask.calculateQ(p, x)}

            for (value in q[0]){
                assertFalse(value.isNaN())
                assertTrue(value.isFinite())
            }
        }
    }

    @Test
    fun testCalculateQForFormulaDefault(){
        val p = shortArrayOf(15, 17)
        val x = doubleArrayOf(0.3, 1.2, -1.5, 2.2)

        val q = ArrayTask.calculateQ(p, x)

        for (row in q){
            for (value in row){
                assertFalse(value.isNaN())
                assertTrue(value.isFinite())
            }
        }
    }

    @Test
    fun testDifferentFormulasProduceDifferentResults(){
        val x = doubleArrayOf(1.0)

        val p13 = shortArrayOf(13)
        val p7 = shortArrayOf(7)
        val p15 = shortArrayOf(15)

        val q13 = ArrayTask.calculateQ(p13, x)[0][0]
        val q7 = ArrayTask.calculateQ(p7, x)[0][0]
        val q15 = ArrayTask.calculateQ(p15, x)[0][0]

        assertNotEquals(q13, q7, 1e-10)
        assertNotEquals(q13, q15, 1e-10)
    }

    @Test
    fun testFullWorlkFlowWithoutExceptions(){
        assertDoesNotThrow{
            val x = ArrayTask.createX()
            val p = ArrayTask.createP()
            val q = ArrayTask.calculateQ(p, x)

            assertEquals(p.size, q.size)
            assertEquals(x.size, q[0].size)
        }
    }

    @Test
    fun testPrintMatrixDoesWork(){
        val matrix = arrayOf(
            doubleArrayOf(2.0, 4.0, 3.0, 6.0),
            doubleArrayOf(1.0, 5.5, 0.2, 1.0),
        )

        assertDoesNotThrow{
            ArrayTask.printMatrix(matrix)
        }
    }

    @Test
    fun testWithExtremeValues(){
        val p = ArrayTask.createP()
        val x = doubleArrayOf(-9.0, 11.0, -8.999, 10.999)

        val q = ArrayTask.calculateQ(p, x)

        for (row in q){
            for (value in row){
                assertTrue(value.isFinite())
            }
        }

        for (row in q){
            for (value in row){
                assertFalse(value.isNaN())
            }
        }
    }
}