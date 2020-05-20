package de.richargh.partaken.escqrs.customer.domain

import de.richargh.partaken.escqrs.basictypes.domain.Command
import de.richargh.partaken.escqrs.basictypes.domain.Email
import de.richargh.partaken.escqrs.basictypes.domain.Name

interface CustomerCommand: Command

class RegisterCustomer(
        val id: CustomerId,
        val name: Name,
        val email: Email): CustomerCommand
