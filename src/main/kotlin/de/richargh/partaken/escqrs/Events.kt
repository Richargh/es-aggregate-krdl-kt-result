package de.richargh.partaken.escqrs

/**
 * Marker interface for events.
 */
interface Event

interface CustomerEvent: Event

class CustomerRegistered(
        val id: CustomerId,
        val name: Name,
        val email: Email): CustomerEvent
