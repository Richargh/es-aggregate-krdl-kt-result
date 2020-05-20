package de.richargh.partaken.escqrs.basictypes.domain

interface Entity {
    val id: Id

    fun hasSameIdentityAs(other: Entity): Boolean = id == other.id

}