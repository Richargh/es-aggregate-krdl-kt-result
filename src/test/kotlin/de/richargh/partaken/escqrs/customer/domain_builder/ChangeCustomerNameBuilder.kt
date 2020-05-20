package de.richargh.partaken.escqrs.customer.domain_builder

import de.richargh.partaken.escqrs.basictypes.domain.Name
import de.richargh.partaken.escqrs.customer.domain.ChangeCustomerName
import de.richargh.partaken.escqrs.customer.domain.CustomerId

class ChangeCustomerNameBuilder(customerBuilderContext: CustomerBuilderContext) {

    private val id: CustomerId = customerBuilderContext.id.nextCustomerId()
    private var name = Name("Anton")

    fun build() = ChangeCustomerName(
            id,
            name)

    fun withName(name: Name) = apply { this.name = name }

}