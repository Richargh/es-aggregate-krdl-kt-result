package de.richargh.partaken.escqrs

/**
 * Marker interface for commands.
 */
interface Command

interface CustomerCommand: Command

class RegisterCustomer(
        val id: CustomerId,
        val name: Name,
        val email: Email): CustomerCommand

