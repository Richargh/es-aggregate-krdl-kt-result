package de.richargh.partaken.escqrs

fun I() = TestBuilder(BuilderContext())

class TestBuilder(private val builderContext: BuilderContext) {
    val wantTo get() = CommandBuilder(builderContext)
    val have get() = EventBuilder(builderContext)
}

class CommandBuilder(private val builderContext: BuilderContext) {
    fun registerCustomer(): RegisterCustomer {
        return RegisterCustomerBuilder(builderContext).build()
    }
}

class EventBuilder(private val builderContext: BuilderContext) {
    fun registeredCustomer(): CustomerRegistered {
        return CustomerRegisteredBuilder(builderContext).build()
    }
}
