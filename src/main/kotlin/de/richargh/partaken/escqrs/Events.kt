package de.richargh.partaken.escqrs

/**
 * Marker interface for events.
 */
interface Event

interface CustomerEvent: Event

class CustomerRegistered: CustomerEvent
