package de.richargh.partaken.escqrs.customer.domain_builder

import de.richargh.partaken.escqrs.customer.domain.ConfirmCustomerEmail
import de.richargh.partaken.escqrs.customer.domain.RegisterCustomer

interface CustomerCommandBuilder {
    fun registerCustomer(init: RegisterCustomerBuilder.() -> Unit = {}): RegisterCustomer
    fun confirmCustomerEmail(init: ConfirmCustomerEmailBuilder.() -> Unit = {}): ConfirmCustomerEmail
}

class ActualCustomerCommandBuilder(private val customerBuilderContext: CustomerBuilderContext): CustomerCommandBuilder {
    override fun registerCustomer(init: RegisterCustomerBuilder.() -> Unit): RegisterCustomer =
            RegisterCustomerBuilder(customerBuilderContext).build()

    override fun confirmCustomerEmail(init: ConfirmCustomerEmailBuilder.() -> Unit): ConfirmCustomerEmail =
            ConfirmCustomerEmailBuilder().apply(init).build()
}
