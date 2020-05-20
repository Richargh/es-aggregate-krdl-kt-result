package de.richargh.partaken.escqrs.customer.domain_builder

import de.richargh.partaken.escqrs.basictypes.domain.ConfirmationHash
import de.richargh.partaken.escqrs.customer.domain.ConfirmCustomerEmail
import de.richargh.partaken.escqrs.customer.domain.CustomerId

class ConfirmCustomerEmailBuilder(customerBuilderContext: CustomerBuilderContext) {

    private val id: CustomerId = customerBuilderContext.id.nextCustomerId()
    private var confirmationHash = ConfirmationHash("456-123")

    fun build() = ConfirmCustomerEmail(
            id,
            confirmationHash)

    fun withConfirmationHash(confirmationHash: ConfirmationHash) = apply { this.confirmationHash = confirmationHash }
}