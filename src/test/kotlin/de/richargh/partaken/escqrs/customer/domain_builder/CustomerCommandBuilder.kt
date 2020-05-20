package de.richargh.partaken.escqrs.customer.domain_builder

import de.richargh.partaken.escqrs.customer.domain.RegisterCustomer

interface CustomerCommandBuilder {
    fun registerCustomer(): RegisterCustomer
}

class ActualCustomerCommandBuilder(private val customerBuilderContext: CustomerBuilderContext): CustomerCommandBuilder {
    override fun registerCustomer(): RegisterCustomer {
        return RegisterCustomerBuilder(customerBuilderContext).build()
    }
}
