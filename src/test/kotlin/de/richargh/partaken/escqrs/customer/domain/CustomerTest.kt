package de.richargh.partaken.escqrs.customer.domain

import de.richargh.partaken.escqrs.basictypes.domain.ConfirmationHash
import de.richargh.partaken.escqrs.integrating.application_builder.I
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
        val result = testling.notYetPersistedEvents
        assertThat(result).hasSize(1)
        assertThat(result.single()).isInstanceOf(CustomerRegistered::class.java)
    }

    @Test
    fun `CustomerRegistered should contain registered eMailAddress`() {
        val I = I()
        // given
        val cmd = I.wantTo.registerCustomer()

        // when
        val testling = Customer.register(cmd)

        // then
        val result = testling.notYetPersistedEvents.filterIsInstance<CustomerRegistered>().first()
        assertThat(result.email).isEqualTo(cmd.email)
    }

    @Test
    fun `CustomerRegistered should contain registered confirmation hash`() {
        val I = I()
        // given
        val cmd = I.wantTo.registerCustomer()

        // when
        val testling = Customer.register(cmd)

        // then
        val result = testling.notYetPersistedEvents.filterIsInstance<CustomerRegistered>().first()
        assertThat(result.confirmationHash).isEqualTo(cmd.confirmationHash)
    }

    @Test
    fun `CustomerRegistered can be confirmed with the right hash`() {
        val I = I()
        // given
        val rightHash = ConfirmationHash("right")
        val testling = I.haveA.customer { makeUnConfirmed(rightHash) }
        val cmd = I.wantTo.confirmCustomerEmail { withConfirmationHash(rightHash) }

        // when
        testling.handle(cmd)

        // then
        val result = testling.notYetPersistedEvents.filterIsInstance<CustomerEmailConfirmed>().firstOrNull()
        assertThat(result).isNotNull
    }

    @Test
    fun `CustomerRegistered cant be confirmed without the right hash`() {
        val I = I()
        // given
        val rightHash = ConfirmationHash("right")
        val wrongHash = ConfirmationHash("wrong")
        val testling = I.haveA.customer { makeUnConfirmed(rightHash) }
        val cmd = I.wantTo.confirmCustomerEmail { withConfirmationHash(wrongHash) }

        // when
        testling.handle(cmd)

        // then
        val result = testling.notYetPersistedEvents.filterIsInstance<CustomerEmailConfirmationFailed>().firstOrNull()
        assertThat(result).isNotNull
    }

    @Test
    fun `Customer can change his email`() {
        val I = I()
        // given
        val testling = I.haveA.customer { makeConfirmed() }
        val cmd = I.wantTo.changeCustomerEmail()

        // when
        testling.handle(cmd)

        // then
        val result = testling.notYetPersistedEvents.filterIsInstance<CustomerEmailAddressChanged>().firstOrNull()
        assertThat(result).isNotNull
    }

    @Test
    fun `Confirmed Customer can change his email and must confirm the change`() {
        val I = I()
        // given
        val oldHash = ConfirmationHash("old")
        val testling = I.haveA.customer { makeConfirmed(oldHash) }

        val newHash = ConfirmationHash("new")
        testling.handle(I.wantTo.changeCustomerEmail { withConfirmationHash(newHash) })
        val cmd = I.wantTo.confirmCustomerEmail { withConfirmationHash(newHash) }

        // when
        testling.handle(cmd)

        // then
        val result = testling.notYetPersistedEvents.filterIsInstance<CustomerEmailConfirmed>().last()
        assertThat(result.confirmationHash).isEqualTo(newHash)
    }
}
