package chucknorris

import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    println("Input string:")

    val input = scanner.nextLine()

    for (ch in input) {
        print("$ch ")
    }
}