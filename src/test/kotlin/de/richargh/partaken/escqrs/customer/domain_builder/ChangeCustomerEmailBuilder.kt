package de.richargh.partaken.escqrs.customer.domain_builder

import de.richargh.partaken.escqrs.basictypes.domain.ConfirmationHash
import de.richargh.partaken.escqrs.basictypes.domain.Email
import de.richargh.partaken.escqrs.customer.domain.ChangeCustomerEmail
import de.richargh.partaken.escqrs.customer.domain.CustomerId

class ChangeCustomerEmailBuilder(customerBuilderContext: CustomerBuilderContext) {

    private val id: CustomerId = customerBuilderContext.id.nextCustomerId()
    private var confirmationHash = ConfirmationHash("fooo-bar")
    private var email = Email("anton@bant.on")

    fun build() = ChangeCustomerEmail(
            id,
            confirmationHash,
            email)

    fun withConfirmationHash(confirmationHash: ConfirmationHash) = apply { this.confirmationHash = confirmationHash }

    fun withEmail(email: Email) = apply { this.email = email }

}