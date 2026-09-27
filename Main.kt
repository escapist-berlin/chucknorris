package chucknorris

import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    println("Input string:")
    val input = scanner.nextLine()

    println("")
    println("The result:")
//    println(encodeToChuckNorris(input))
    println(decodeFromChuckNorris(input))
}

fun encodeToChuckNorris(input: String): String {
    if (input.isEmpty()) return ""

    val binary = input
        .map { to7BitBinary(it) }
        .joinToString("")

    val encodedRuns = mutableListOf<String>()
    var currentBit = binary.first()
    var count = 1

    for (bit in binary.substring(1)) {
        if (bit == currentBit) {
            count++
        } else {
            encodedRuns.add(encodeRun(currentBit, count))
            currentBit = bit
            count = 1
        }
    }
    encodedRuns.add(encodeRun(currentBit, count))

    return encodedRuns.joinToString(" ")
}

private fun to7BitBinary(ch: Char): String {
    return String.format("%7s", Integer.toBinaryString(ch.code))
        .replace(' ', '0')
}

private fun encodeRun(bit: Char, count: Int): String {
    val bitMarker = if (bit == '0') "00" else "0"
    val repeatMarker = "0".repeat(count)
    return "$bitMarker $repeatMarker"
}

fun decodeFromChuckNorris(encoded: String): String {
    if (encoded.isEmpty()) return ""

    val parts = encoded.trim().split(Regex("\\s+"))
    val binaryString = parts
        .chunked(2)
        .joinToString("") { (bitMarker, repeatMarker) ->
            decodeRun(bitMarker, repeatMarker)
        }

    return binaryString
        .chunked(7)
        .map { binaryToChar(it) }
        .joinToString("")
}

private fun decodeRun(bitMarker: String, repeatMarker: String): String {
    val bit = if (bitMarker == "00") '0' else '1'
    return bit.toString().repeat(repeatMarker.length)
}

private fun binaryToChar(binary: String): Char {
    return Integer.parseInt(binary, 2).toChar()
}