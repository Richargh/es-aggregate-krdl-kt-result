package de.richargh.partaken.escqrs

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CustomerTest {

    @Test
    fun `Aggregate should be created via factory method`() {
        // given

        // when
        val testling = Customer.register(RegisterCustomer())

        // then
        assertThat(testling.recordedEvents).hasSize(1)
        assertThat(testling.recordedEvents.single()).isInstanceOf(CustomerRegistered::class.java)
    }

    @Test
    fun `CustomerRegistered should record CustomerRegistered`() {
        // given

        // when

        // then

    }

    @Test
    fun `CustomerRegistered should contain registered eMailAddress`() {
        // given

        // when

        // then

    }
}

