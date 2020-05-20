package de.richargh.partaken.escqrs.customer.domain

import de.richargh.partaken.escqrs.basictypes.domain.Command
import de.richargh.partaken.escqrs.basictypes.domain.ConfirmationHash
import de.richargh.partaken.escqrs.basictypes.domain.Email
import de.richargh.partaken.escqrs.basictypes.domain.Name

interface CustomerCommand: Command

class RegisterCustomer(
        val id: CustomerId,
        val name: Name,
        val email: Email,
        val confirmationHash: ConfirmationHash): CustomerCommand

class ConfirmCustomerEmail(
        val id: CustomerId,
        val confirmationHash: ConfirmationHash): CustomerCommand

class ChangeCustomerEmail(
        val id: CustomerId,
        val confirmationHash: ConfirmationHash,
        val email: Email): CustomerCommand