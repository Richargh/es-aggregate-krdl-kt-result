package de.richargh.partaken.escqrs.integrating.application_builder

import de.richargh.partaken.escqrs.customer.domain_builder.*

fun I() = TestBuilder()

class TestBuilder {
    private val customerBuilderContext = CustomerBuilderContext()
    val wantTo = CommandBuilder(
            ActualCustomerCommandBuilder(customerBuilderContext))
    val did = EventBuilder(
            ActualCustomerEventBuilder(customerBuilderContext))

    val haveA = DomainBuilder(
            ActualCustomerDomainBuilder(customerBuilderContext))
    val requireA = AppBuilder(
            ActualCustomerAppBuilder())
}

class AppBuilder(
        private val customerAppBuilder: ActualCustomerAppBuilder) {

    val customer: CustomerAppBuilder get() = customerAppBuilder
}

class DomainBuilder(
        private val customerDomainBuilder: ActualCustomerDomainBuilder):
        CustomerDomainBuilder by customerDomainBuilder

class CommandBuilder(
        private val customerCommandBuilder: CustomerCommandBuilder):
        CustomerCommandBuilder by customerCommandBuilder

class EventBuilder(
        private val customerEventBuilder: CustomerEventBuilder):
        CustomerEventBuilder by customerEventBuilder
