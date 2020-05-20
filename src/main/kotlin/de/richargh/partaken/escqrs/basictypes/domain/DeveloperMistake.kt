package de.richargh.partaken.escqrs.basictypes.domain

import java.lang.RuntimeException

class DeveloperMistake(message: String): RuntimeException(message)