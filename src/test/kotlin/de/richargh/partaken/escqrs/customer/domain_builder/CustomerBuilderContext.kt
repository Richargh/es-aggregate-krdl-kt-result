package de.richargh.partaken.escqrs.customer.domain_builder

import de.richargh.partaken.escqrs.customer.domain_builder.CustomerIdGenerator

class CustomerBuilderContext {

    val id = CustomerIdGenerator()

}
