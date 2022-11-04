package example4

fun main(args: Array<String>) {
    when(val str = KNonEmptyString.of("")) {
        is KEither.Left -> println("Error: ${str.value.value}")
        is KEither.Right -> println("Success: ${str.value}")
    }
}
