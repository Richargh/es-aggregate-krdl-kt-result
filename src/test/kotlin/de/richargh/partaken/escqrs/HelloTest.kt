package de.richargh.partaken.escqrs

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HelloTest {

    @Test
    fun `should provide nice greeting`(){
        // given
        val testling = Hello()

        // when
        val result = testling.speak()

        // then
        assertThat(result).isEqualTo("Sup")
    }
}