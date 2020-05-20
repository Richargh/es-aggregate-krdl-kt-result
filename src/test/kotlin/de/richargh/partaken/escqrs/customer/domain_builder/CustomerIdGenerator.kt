package de.richargh.partaken.escqrs.customer.domain_builder

import de.richargh.partaken.escqrs.customer.domain.CustomerId

class CustomerIdGenerator {

    private var customerId = 0
    fun nextCustomerId() = CustomerId("${customerId++}")
}
