package de.richargh.partaken.escqrs.customer.domain_builder

import de.richargh.partaken.escqrs.customer.domain.CustomerRegistered

interface CustomerEventBuilder {
    fun registeredCustomer(): CustomerRegistered
}

class ActualCustomerEventBuilder(private val customerBuilderContext: CustomerBuilderContext): CustomerEventBuilder {
    override fun registeredCustomer(): CustomerRegistered {
        return CustomerRegisteredBuilder(customerBuilderContext).build()
    }
}
