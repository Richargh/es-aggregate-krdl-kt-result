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

    fun handle(command: CustomerCommand) {
        val event = when (command) {
            is RegisterCustomer     -> with(command) {
                CustomerRegistered(id, confirmationHash, name, email)
            }
            is ConfirmCustomerEmail -> with(command) {
                if (confirmationHash == lastConfirmationHash)
                    RelatedOutcome.Ok(CustomerEmailConfirmed(id, confirmationHash))
                else
                    RelatedOutcome.Fail(CustomerEmailConfirmationFailed(id, confirmationHash))
            }.value
            is ChangeCustomerEmail  -> with(command) {
                CustomerEmailAddressChanged(id, confirmationHash, email)
            }
            is ChangeCustomerName   -> with(command) {
                CustomerNameChanged(id, name)
            }
        }
        applyAndRecord(event)
    }

    fun reconstitute(events: List<CustomerEvent>) {
        events.forEach(::reconstitute)
    }

    private fun reconstitute(event: CustomerEvent) = apply(event, shouldRecord = false)
    private fun applyAndRecord(event: CustomerEvent) = apply(event, shouldRecord = true)
    private fun apply(event: CustomerEvent, shouldRecord: Boolean) {
        val knownEvent: CustomerEvent = when (event) {
            is CustomerRegistered              -> {
                name = event.name
                email = event.email
                lastConfirmationHash = event.confirmationHash
                event
            }
            is CustomerEmailConfirmed          -> {
                state = CustomerState.CONFIRMED
                lastConfirmationHash = null
                event
            }
            is CustomerEmailConfirmationFailed -> {
                event
            }
            is CustomerEmailAddressChanged     -> {
                state = CustomerState.UNCONFIRMED
                email = event.email
                lastConfirmationHash = event.confirmationHash
                event
            }
            is CustomerNameChanged             -> {
                state = CustomerState.UNCONFIRMED
                name = event.name
                event
            }
        }
        if (shouldRecord) events.add(knownEvent)
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
