import java.io.File


fun main(args: Array<String>) {
    val input = File("src/input_nine.txt").readText()
    println(length(input))
}

fun length(s: String): Long {
    return length(0, s.length, s)
}

fun length(start: Int, end: Int, s: String): Long {
    if (start >= s.length || end <= start)
        return 0L

    if (s[start] != '(')
        return 1L + length(start + 1, end, s)

    val marker  = parseMarker(s, start)
    val startNext = start + marker.length
    val endNext = startNext + marker.numChars
    return marker.repetitions * length(startNext, endNext, s) + length(endNext, end, s)
}


data class Marker(val length: Int, val numChars: Int, val repetitions: Long)
fun parseMarker(input: String, index: Int): Marker {
    var i = index + 1
    var sb = StringBuilder()
    while (input[i].isDigit())
        sb.append(input[i++])
    val numChars = sb.toString().toInt()

    i++ // skip x

    sb = StringBuilder()
    while (input[i].isDigit())
        sb.append(input[i++])
    val repetitions = sb.toString().toLong()

    val length = i - index + 1
    return Marker(length, numChars, repetitions)
}
