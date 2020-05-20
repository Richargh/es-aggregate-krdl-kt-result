package de.richargh.partaken.escqrs.basictypes.domain

sealed class Either<out A, out B, C> where A: C, B: C, C: Any {
    class Left<A: C, C: Any>(override val value: A): Either<A, Nothing, C>()
    class Right<B: C, C: Any>(override val value: B): Either<Nothing, B, C>()

    abstract val value: C

    val left: A
        get() = if (this is Left) value
        else throw DeveloperMistake("Dont call left on [$this] that only has right value")

    val right: B
        get() = if (this is Right) value
        else throw DeveloperMistake("Dont call right on [$this] that only has left value")
}

typealias EitherAny<A, B> = Either<A, B, Any>