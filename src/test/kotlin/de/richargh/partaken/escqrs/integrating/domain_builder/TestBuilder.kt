package de.richargh.partaken.escqrs.integrating.domain_builder

import de.richargh.partaken.escqrs.customer.domain_builder.CustomerBuilderContext
import de.richargh.partaken.escqrs.customer.domain_builder.ActualCustomerCommandBuilder
import de.richargh.partaken.escqrs.customer.domain_builder.ActualCustomerEventBuilder
import de.richargh.partaken.escqrs.customer.domain_builder.CustomerCommandBuilder
import de.richargh.partaken.escqrs.customer.domain_builder.CustomerEventBuilder

fun I() = TestBuilder()

class TestBuilder {
    private val customerBuilderContext = CustomerBuilderContext()
    val wantTo = CommandBuilder(ActualCustomerCommandBuilder(customerBuilderContext))
    val have = EventBuilder(ActualCustomerEventBuilder(customerBuilderContext))
}

class CommandBuilder(
        private val customerCommandBuilder: CustomerCommandBuilder):
        CustomerCommandBuilder by customerCommandBuilder

class EventBuilder(
        private val customerEventBuilder: CustomerEventBuilder):
        CustomerEventBuilder by customerEventBuilder
