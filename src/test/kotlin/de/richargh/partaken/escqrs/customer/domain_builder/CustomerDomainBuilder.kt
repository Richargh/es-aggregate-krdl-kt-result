package de.richargh.partaken.escqrs.customer.domain_builder

import de.richargh.partaken.escqrs.customer.domain.Customer

interface CustomerDomainBuilder{
    fun customer(init: CustomerBuilder.() -> Unit = {}): Customer
}

class ActualCustomerDomainBuilder(
        private val customerBuilderContext: CustomerBuilderContext): CustomerDomainBuilder {
    override fun customer(init: CustomerBuilder.() -> Unit): Customer
            = CustomerBuilder(customerBuilderContext).apply(init).build()
}