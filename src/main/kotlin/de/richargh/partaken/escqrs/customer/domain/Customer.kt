package de.richargh.partaken.escqrs.customer.domain

import de.richargh.partaken.escqrs.basictypes.domain.Id
import de.richargh.partaken.escqrs.basictypes.domain.*

data class CustomerId(val rawValue: String): Id

sealed class CustomerState {
    object UNCONFIRMED: CustomerState()
    object CONFIRMED: CustomerState()
}

data class Customer(
        override val id: CustomerId,
        private var state: CustomerState = CustomerState.UNCONFIRMED,
        private var name: Name = Name.NULL,
        private var email: Email = Email.NULL): Aggregate {

    private val events: MutableList<CustomerEvent> = mutableListOf()
    val recordedEvents: List<CustomerEvent> get() = events

    fun apply(events: List<CustomerEvent>) {
        events.forEach(::apply)
    }
    fun apply(event: CustomerEvent) {
        when (event) {
            is CustomerRegistered -> {
                name = event.name
                email = event.email
                events.add(event)
            }
            is CustomerEmailConfirmed -> {
                state = CustomerState.CONFIRMED
                events.add(event)
            }
            is CustomerEmailConfirmationFailed -> {
                events.add(event)
            }
        }
    }

    fun handle(command: CustomerCommand) {
        when (command) {
            is RegisterCustomer -> registerCustomer(command).let(::apply)
            is ConfirmCustomerEmail -> confirmCustomerEmail(command).value.let(::apply)
        }
    }

    private fun registerCustomer(cmd: RegisterCustomer): CustomerRegistered {
        return with(cmd) {
            CustomerRegistered(id, name, email, confirmationHash)
        }
    }

    private fun confirmCustomerEmail(cmd: ConfirmCustomerEmail): RelatedOutcome<CustomerEmailConfirmed, CustomerEmailConfirmationFailed, CustomerEvent> {
        return with(cmd) {
            if(confirmationHash == events.filterIsInstance<CustomerRegistered>().lastOrNull()?.confirmationHash)
                RelatedOutcome.Ok(CustomerEmailConfirmed(confirmationHash))
            else
                RelatedOutcome.Fail(CustomerEmailConfirmationFailed(confirmationHash))
        }
    }

    companion object {
        fun register(cmd: RegisterCustomer): Customer {
            return Customer(
                    cmd.id,
                    CustomerState.UNCONFIRMED,
                    Name.NULL,
                    Email.NULL).apply {
                handle(cmd)
            }
        }
    }
}
