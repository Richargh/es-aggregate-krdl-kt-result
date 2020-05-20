package de.richargh.partaken.escqrs

interface Entity {
    val id: Id

    fun hasSameIdentityAs(other: Entity): Boolean = id == other.id

}