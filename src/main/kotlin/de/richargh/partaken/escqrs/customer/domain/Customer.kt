package de.richargh.partaken.escqrs.customer.domain

import de.richargh.partaken.escqrs.basictypes.domain.Id
import de.richargh.partaken.escqrs.basictypes.domain.*

data class CustomerId(val rawValue: String): Id

sealed class CustomerState {
    object UNCONFIRMED: CustomerState()
    object CONFIRMED: CustomerState()
}

data class Customer(
        override val id: CustomerId): Aggregate {

    private val events: MutableList<CustomerEvent> = mutableListOf()
    val notYetPersistedEvents: List<CustomerEvent> get() = events

    private var state: CustomerState = CustomerState.UNCONFIRMED
    private var name: Name = Name.NULL
    private var email: Email = Email.NULL
    private var lastConfirmationHash: ConfirmationHash? = null

    fun reconstitute(events: List<CustomerEvent>) {
        events.forEach(::reconstitute)
    }

    private fun reconstitute(event: CustomerEvent) = apply(event, false)
    private fun record(event: CustomerEvent) = apply(event, true)
    private fun apply(event: CustomerEvent, shouldAddEvent: Boolean) {
        when (event) {
            is CustomerRegistered              -> {
                name = event.name
                email = event.email
                lastConfirmationHash = event.confirmationHash
                if (shouldAddEvent) events.add(event)
            }
            is CustomerEmailConfirmed          -> {
                state = CustomerState.CONFIRMED
                lastConfirmationHash = null
                if (shouldAddEvent) events.add(event)
            }
            is CustomerEmailConfirmationFailed -> {
                if (shouldAddEvent) events.add(event)
            }
            is CustomerEmailAddressChanged     -> {
                state = CustomerState.UNCONFIRMED
                lastConfirmationHash = event.confirmationHash
                if (shouldAddEvent) events.add(event)
            }
        }
    }

    fun handle(command: CustomerCommand) {
        when (command) {
            is RegisterCustomer     -> registerCustomer(command).let(::record)
            is ConfirmCustomerEmail -> confirmCustomerEmail(command).value.let(::record)
            is ChangeCustomerEmail  -> changeCustomerEmail(command).let(::record)
        }
    }

    private fun registerCustomer(cmd: RegisterCustomer): CustomerRegistered {
        return with(cmd) {
            CustomerRegistered(id, name, email, confirmationHash)
        }
    }

    private fun confirmCustomerEmail(
            cmd: ConfirmCustomerEmail): RelatedOutcome<CustomerEmailConfirmed, CustomerEmailConfirmationFailed, CustomerEvent> {
        return with(cmd) {
            if (confirmationHash == lastConfirmationHash)
                RelatedOutcome.Ok(CustomerEmailConfirmed(id, confirmationHash))
            else
                RelatedOutcome.Fail(CustomerEmailConfirmationFailed(id, confirmationHash))
        }
    }

    private fun changeCustomerEmail(cmd: ChangeCustomerEmail): CustomerEmailAddressChanged {
        return with(cmd) {
            CustomerEmailAddressChanged(id, confirmationHash)
        }
    }

    companion object {
        fun register(cmd: RegisterCustomer): Customer {
            return Customer(
                    cmd.id).apply {
                handle(cmd)
            }
        }
    }
}
