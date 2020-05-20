package de.richargh.partaken.escqrs.customer.domain_builder

import de.richargh.partaken.escqrs.basictypes.domain.ConfirmationHash
import de.richargh.partaken.escqrs.customer.domain.ConfirmCustomerEmail

class ConfirmCustomerEmailBuilder {

    private var confirmationHash = ConfirmationHash("456-123")

    fun build() = ConfirmCustomerEmail(
            confirmationHash)

    fun withConfirmationHash(confirmationHash: ConfirmationHash) = apply { this.confirmationHash = confirmationHash }
}