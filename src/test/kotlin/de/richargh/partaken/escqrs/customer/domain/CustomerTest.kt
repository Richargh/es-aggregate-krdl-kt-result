package de.richargh.partaken.escqrs.customer.domain

import de.richargh.partaken.escqrs.integrating.domain_builder.I
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CustomerTest {

    @Test
    fun `Aggregate should be created via factory method`() {
        val I = I()
        // given

        // when
        val testling = Customer.register(I.wantTo.registerCustomer())

        // then
        assertThat(testling.recordedEvents).hasSize(1)
        assertThat(testling.recordedEvents.single()).isInstanceOf(CustomerRegistered::class.java)
    }

    @Test
    fun `CustomerRegistered should contain registered eMailAddress`() {
        val I = I()
        // given
        val cmd = I.wantTo.registerCustomer()

        // when
        val testling = Customer.register(cmd)

        // then
        val customerRegistered = testling.recordedEvents.filterIsInstance(CustomerRegistered::class.java).first()
        assertThat(customerRegistered.email).isEqualTo(cmd.email)
    }
}
