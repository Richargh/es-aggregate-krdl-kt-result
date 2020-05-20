package de.richargh.partaken.escqrs

data class CustomerId(val rawValue: String): Id

data class Customer private constructor(override val id: CustomerId): Aggregate {

    private val events: MutableList<CustomerEvent> = mutableListOf()
    val recordedEvents: List<CustomerEvent> get() = events

    private var name: Name = Name.NULL
    private var email: Email = Email.NULL

    fun apply(event: CustomerEvent) {
        when(event){
            is CustomerRegistered -> {
                name = event.name
                email = event.email
            }
        }
    }

    fun handle(command: CustomerCommand) {
        when(command){
            is RegisterCustomer -> registerCustomer(command).let(::apply)
        }
    }

    private fun registerCustomer(cmd: RegisterCustomer): CustomerRegistered {
        return with(cmd){
            CustomerRegistered(id, name, email)
        }
    }

    companion object {
        fun register(cmd: RegisterCustomer): Customer {
            return Customer(cmd.id).apply {
                handle(cmd)
            }
        }

    }
}