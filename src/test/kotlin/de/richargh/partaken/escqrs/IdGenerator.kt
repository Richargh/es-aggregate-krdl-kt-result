package de.richargh.partaken.escqrs

class IdGenerator {

    private var customerId = 0
    fun nextCustomerId() = CustomerId("${customerId++}")
}