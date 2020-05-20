package de.richargh.partaken.escqrs.customer.domain_builder

import de.richargh.partaken.escqrs.basictypes.domain.ConfirmationHash
import de.richargh.partaken.escqrs.basictypes.domain.Email
import de.richargh.partaken.escqrs.basictypes.domain.Name
import de.richargh.partaken.escqrs.customer.domain.*

class CustomerBuilder(customerBuilderContext: CustomerBuilderContext) {

    private var id = customerBuilderContext.id.nextCustomerId()
    private var name = Name("Benny")
    private var email = Email("blub@ben.de")
    private var state: CustomerState = CustomerState.UNCONFIRMED
    private var events: List<CustomerEvent> = listOf(
            CustomerRegisteredBuilder(customerBuilderContext).build())

    fun build() = Customer(id).apply { apply(events) }

    fun makeConfirmed(confirmationHash: ConfirmationHash) = apply {
        this.events = listOf(
                CustomerRegistered(id, name, email, confirmationHash),
                CustomerEmailConfirmed(confirmationHash))
    }
    fun makeUnConfirmed(confirmationHash: ConfirmationHash) = apply {
        this.events = listOf(
                CustomerRegistered(id, name, email, confirmationHash))
    }
}