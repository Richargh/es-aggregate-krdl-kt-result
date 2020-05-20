package de.richargh.partaken.escqrs.basictypes.application

/**
 * Marker interface.
 *
 * A gate is the only way to call domain code from outside.
 */
interface Gate

/**
 * Marker interface for gates that handle queries.
 */
interface ReadGate: Gate

/**
 * Marker interface for gates that handle state changes.
 */
interface WriteGate: Gate