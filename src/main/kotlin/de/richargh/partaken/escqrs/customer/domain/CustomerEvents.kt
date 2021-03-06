package de.richargh.partaken.escqrs.customer.domain

import de.richargh.partaken.escqrs.basictypes.domain.ConfirmationHash
import de.richargh.partaken.escqrs.basictypes.domain.Email
import de.richargh.partaken.escqrs.basictypes.domain.Event
import de.richargh.partaken.escqrs.basictypes.domain.Name

sealed class CustomerEvent: Event

class CustomerRegistered(
        val id: CustomerId,
        val confirmationHash: ConfirmationHash,
        val name: Name,
        val email: Email): CustomerEvent()

class CustomerEmailConfirmed(
        val id: CustomerId,
        val confirmationHash: ConfirmationHash): CustomerEvent()

class CustomerEmailConfirmationFailed(
        val id: CustomerId,
        val confirmationHash: ConfirmationHash): CustomerEvent()

class CustomerEmailAddressChanged(
        val id: CustomerId,
        val confirmationHash: ConfirmationHash,
        val email: Email): CustomerEvent()

class CustomerNameChanged(
        val id: CustomerId,
        val name: Name): CustomerEvent()