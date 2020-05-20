package de.richargh.partaken.escqrs.customer.domain_builder

import de.richargh.partaken.escqrs.customer.domain.ChangeCustomerEmail
import de.richargh.partaken.escqrs.customer.domain.ConfirmCustomerEmail
import de.richargh.partaken.escqrs.customer.domain.RegisterCustomer

interface CustomerCommandBuilder {
    fun registerCustomer(init: RegisterCustomerBuilder.() -> Unit = {}): RegisterCustomer
    fun confirmCustomerEmail(init: ConfirmCustomerEmailBuilder.() -> Unit = {}): ConfirmCustomerEmail
    fun changeCustomerEmail(init: ChangeCustomerEmailBuilder.() -> Unit = {}): ChangeCustomerEmail
}

class ActualCustomerCommandBuilder(private val customerBuilderContext: CustomerBuilderContext): CustomerCommandBuilder {
    override fun registerCustomer(init: RegisterCustomerBuilder.() -> Unit): RegisterCustomer =
            RegisterCustomerBuilder(customerBuilderContext).apply(init).build()

    override fun confirmCustomerEmail(init: ConfirmCustomerEmailBuilder.() -> Unit): ConfirmCustomerEmail =
            ConfirmCustomerEmailBuilder(customerBuilderContext).apply(init).build()

    override fun changeCustomerEmail(init: ChangeCustomerEmailBuilder.() -> Unit): ChangeCustomerEmail =
            ChangeCustomerEmailBuilder(customerBuilderContext).apply(init).build()
}
