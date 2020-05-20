package de.richargh.partaken.escqrs.customer.domain

import de.richargh.partaken.escqrs.basictypes.domain.Email
import de.richargh.partaken.escqrs.basictypes.domain.Event
import de.richargh.partaken.escqrs.basictypes.domain.Name

interface CustomerEvent: Event

class CustomerRegistered(
        val id: CustomerId,
        val name: Name,
        val email: Email): CustomerEvent
