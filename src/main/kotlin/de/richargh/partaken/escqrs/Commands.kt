package de.richargh.partaken.escqrs

/**
 * Marker interface for commands.
 */
interface Command

interface CustomerCommand: Command

class RegisterCustomer: CustomerCommand

