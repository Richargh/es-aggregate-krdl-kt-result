package de.richargh.partaken.escqrs.basictypes.domain

data class Name(val rawValue: String): ValueObject {
    companion object{
        val NULL = Name("NULL")
    }
}

data class Email(val rawValue: String): ValueObject {
    companion object{
        val NULL = Email("NULL")
    }
}
