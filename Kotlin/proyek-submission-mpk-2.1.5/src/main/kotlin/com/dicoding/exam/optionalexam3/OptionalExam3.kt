package com.dicoding.exam.optionalexam3

// TODO
fun manipulateString(str: String, int: Int): String {
    val letters = str.takeWhile {
        it.isLetter()
    }
    val digitsPart = str.dropWhile {
        it.isLetter()
    }
    return if (digitsPart.isNotEmpty()) {
        val numberFromStr = digitsPart.toInt()
        val multiplied = numberFromStr * int
        letters + multiplied.toString()
    } else {
        str + int.toString()
    }
    // return ""
}
