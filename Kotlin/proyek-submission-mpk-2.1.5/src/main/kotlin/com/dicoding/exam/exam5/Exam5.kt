package com.dicoding.exam.exam5
import kotlinx.coroutines.delay
// TODO 1
suspend fun sum(valueA: Int, valueB: Int): Int {
    delay(3000)
    return valueA + valueB
    // return 0
}

// TODO 2
suspend fun multiple(valueA: Int, valueB: Int): Int {
    delay(2000)
    return valueA * valueB
    // return 0
}
