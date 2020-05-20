package de.richargh.partaken.escqrs.basictypes.domain

sealed class RelatedOutcome<out OK, out KO, SUPER> where OK: SUPER, KO: SUPER, SUPER: Any {
    class Ok<OK: SUPER, SUPER: Any>(override val value: OK): RelatedOutcome<OK, Nothing, SUPER>()
    class Fail<KO: SUPER, SUPER: Any>(override val value: KO): RelatedOutcome<Nothing, KO, SUPER>()

    abstract val value: SUPER

    val ok: OK
        get() = if (this is Ok) value
        else throw DeveloperMistake("Dont call ok on [$this] that only has failed value")

    val fail: KO
        get() = if (this is Fail) value
        else throw DeveloperMistake("Dont call fail on [$this] that only has ok value")
}

typealias Outcome<OK, KO> = RelatedOutcome<OK, KO, Any>

typealias EventOutcome<OK, KO> = RelatedOutcome<OK, KO, Event>

