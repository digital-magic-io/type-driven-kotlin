package example5

import arrow.core.Invalid
import arrow.core.Valid
import arrow.core.Validated
import arrow.core.ValidatedNel
import arrow.core.invalid
import arrow.core.invalidNel
import arrow.core.valid
import arrow.core.validNel
import arrow.core.zip

@JvmInline
value class KError(val value: String)

@JvmInline
value class NonEmptyString private constructor(val value: String) {
    companion object {
        fun of(value: String): Validated<KError, NonEmptyString> =
            value.takeUnless { value.isEmpty() } ?.let { NonEmptyString(it).valid() }
                ?: KError("Value can't be null or empty!").invalid()
    }
}

@JvmInline
value class PositiveInt private constructor(val value: Int) {
    companion object {
        fun of(value: Int): Validated<KError, PositiveInt> =
            value.takeUnless { it < 0 } ?.let { PositiveInt(it).valid() }
                ?: KError("Value can't be negative!").invalid()
    }
}
data class Person private constructor(
    val firstname: NonEmptyString,
    val lastname: NonEmptyString,
    val age: PositiveInt,
    val kids: PositiveInt
) {
    companion object {
        fun of(firstname: String, lastname: String, age: Int, kids: Int): ValidatedNel<Error, Person> {
            val firstnameValidated = Person::firstname.name to NonEmptyString.of(firstname)
            val lastnameValidated = Person::lastname.name to NonEmptyString.of(lastname)
            val ageValidated = Person::age.name to PositiveInt.of(age)
            val kidsValidated = Person::kids.name to PositiveInt.of(kids)
            listOf(firstnameValidated, lastnameValidated, ageValidated, kidsValidated).let { results ->
                if (results.any { it.second.isInvalid }) {
                    return results.filter { it.second.isInvalid }
                        .map { e -> (e.second as Invalid).mapLeft { Error("${e.first}: ${it.value}") }  }
                        .invalidNel() as ValidatedNel<Error, Person>
                } else {
                    return Person(
                        (firstnameValidated.second as Valid).value,
                        (lastnameValidated.second as Valid).value,
                        (ageValidated.second as Valid).value,
                        (kidsValidated.second as Valid).value
                    ).validNel()
                }
            }
        }
    }
}


fun main(args: Array<String>) {


    when (val v = PositiveInt.of(-1)) {
        is Valid -> print("Success: ${v.value}")
        is Invalid -> println("Error: ${v.value}")
    }

    when (val p = Person.of("", "", -1, -1)) {
        is Validated.Valid -> println("Success: ${p.value}")
        is Validated.Invalid -> println("Error: ${p.value}")
    }
}
