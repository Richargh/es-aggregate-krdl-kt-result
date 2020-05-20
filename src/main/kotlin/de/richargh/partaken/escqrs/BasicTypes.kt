package de.richargh.partaken.escqrs

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
