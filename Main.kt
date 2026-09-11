package chucknorris

import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    println("Input string:")
    val input = scanner.nextLine()

    println("")
    println("The result:")

    for (ch in input) {
        val binaryString = Integer.toBinaryString(ch.code)
        val paddedBinaryString = String.format("%07d", binaryString.toInt())

        println("$ch = $paddedBinaryString")
    }
}