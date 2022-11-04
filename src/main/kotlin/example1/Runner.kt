package example1

import example2.KPasswordChange2

fun main(args: Array<String>) {
    val correct = NonEmptyString("test");
    println(correct)
    println(correct.value)

    val correct2 = KNonEmptyString("test")
    println(correct2)
    println(correct2.value)

    val pass = KPasswordChange2("testtest1", "testtest", "testtest");
    println(pass);

    KNonEmptyString("")
    NonEmptyString("");
}
