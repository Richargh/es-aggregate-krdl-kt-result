package de.richargh.partaken.escqrs


interface Event

interface CustomerEvent: Event

class CustomerRegistered: CustomerEvent
