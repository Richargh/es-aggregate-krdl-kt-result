package de.richargh.partaken.escqrs.customer.domain_builder

import de.richargh.partaken.escqrs.basictypes.domain.ConfirmationHash
import de.richargh.partaken.escqrs.customer.domain.CustomerId
import de.richargh.partaken.escqrs.basictypes.domain.Email
import de.richargh.partaken.escqrs.basictypes.domain.Name
import de.richargh.partaken.escqrs.customer.domain.CustomerRegistered

class CustomerRegisteredBuilder(customerBuilderContext: CustomerBuilderContext) {

    private val id: CustomerId = customerBuilderContext.id.nextCustomerId()
    private val name: Name = Name("Blubb")
    private val email: Email = Email("foo@bar.de")
    private val confirmationHash = ConfirmationHash("1234-5678-90")

    fun build() = CustomerRegistered(
            id,
            confirmationHash,
            name,
            email)
}
