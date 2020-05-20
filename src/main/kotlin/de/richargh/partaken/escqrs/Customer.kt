package de.richargh.partaken.escqrs

class Customer private constructor(
        private val id: CustomerId,
        private val name: Name,
        private val email: Email,
        initialEvents: List<CustomerEvent>): Aggregate {

    private val events: MutableList<CustomerEvent> = mutableListOf()
    val recordedEvents: List<CustomerEvent> get() = events

    init {
        initialEvents.forEach { events.add(it) }
    }

    fun handle(command: CustomerCommand) {
    }

    companion object {
        fun register(cmd: RegisterCustomer): Customer {
            val event = registerCustomer(cmd)
            return with(event){
                Customer(id, name, email, listOf(event))
            }
        }

        private fun registerCustomer(cmd: RegisterCustomer): CustomerRegistered {
            return with(cmd){
                CustomerRegistered(id, name, email)
            }
        }
    }
}