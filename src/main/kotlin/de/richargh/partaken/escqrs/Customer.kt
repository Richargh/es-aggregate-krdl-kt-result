package de.richargh.partaken.escqrs

class Customer private constructor(initialEvents: List<CustomerEvent>): Aggregate {

    private val events: MutableList<CustomerEvent> = mutableListOf()

    val recordedEvents: List<CustomerEvent> get() = events

    init {
        initialEvents.forEach { events.add(it) }
    }

    fun handle(command: CustomerCommand) {
        when (command) {
            is RegisterCustomer -> events.add(CustomerRegistered())
        }
    }

    companion object {
        fun register(vararg commands: CustomerCommand): Customer {
            val customer = Customer(emptyList())
            commands.forEach(customer::handle)
            return customer
        }
    }
}