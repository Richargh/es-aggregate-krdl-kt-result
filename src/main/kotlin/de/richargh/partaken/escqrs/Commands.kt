package de.richargh.partaken.escqrs

interface Command

interface CustomerCommand: Command

class RegisterCustomer: CustomerCommand

