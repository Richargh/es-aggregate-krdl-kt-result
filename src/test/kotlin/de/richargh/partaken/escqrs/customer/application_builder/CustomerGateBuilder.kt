package de.richargh.partaken.escqrs.customer.application_builder

import de.richargh.partaken.escqrs.customer.application.CustomerReadGate
import de.richargh.partaken.escqrs.customer.application.CustomerWriteGate

class CustomerGateBuilder {
    fun build(): Pair<CustomerWriteGate, CustomerReadGate>{
        return CustomerWriteGate() to CustomerReadGate()
    }


}