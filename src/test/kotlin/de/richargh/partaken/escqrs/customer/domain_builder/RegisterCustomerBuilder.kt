package de.richargh.partaken.escqrs.customer.domain_builder

import de.richargh.partaken.escqrs.basictypes.domain.ConfirmationHash
import de.richargh.partaken.escqrs.basictypes.domain.Email
import de.richargh.partaken.escqrs.basictypes.domain.Name
import de.richargh.partaken.escqrs.customer.domain.RegisterCustomer

class RegisterCustomerBuilder(customerBuilderContext: CustomerBuilderContext) {

    private val id = customerBuilderContext.id.nextCustomerId()
    private val name = Name("Blubb")
    private val email = Email("foo@bar.de")
    private var confirmationHash = ConfirmationHash("1234-5678-90")

    fun build() = RegisterCustomer(
            id,
            name,
            email,
            confirmationHash)

    fun withConfirmationHash(confirmationHash: ConfirmationHash) = apply { this.confirmationHash = confirmationHash }
}
