package de.richargh.partaken.escqrs

class RegisterCustomerBuilder(builderContext: BuilderContext) {

    private val id: CustomerId = builderContext.id.nextCustomerId()
    private val name: Name = Name("Blubb")
    private val email: Email = Email("foo@bar.de")

    fun build() = RegisterCustomer(
            id,
            name,
            email)
}