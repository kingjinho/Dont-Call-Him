package com.kingjinho.dontcallhim.utils

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.google.common.truth.Truth.assertThat

@RunWith(JUnit4::class)
class PhoneNumberValidationTest {

    @Test
    fun `isValidPhoneNumber()_01011111111_isInvalidNumber`() {
        assertThat("01011111111".isValidPhoneNumber()).isFalse()
    }

    @Test
    fun `isValidPhoneNumber()_010-1111-1111_isValidNumber`() {
        assertThat("010-1111-1111".isValidPhoneNumber()).isTrue()
    }

    @Test
    fun `isValidPhoneNumber()_011-111-1111_isValidNumber`() {
        assertThat("011-111-1111".isValidPhoneNumber()).isTrue()
    }

    @Test
    fun `isValidPhoneNumber()_011111-1111_isInvalidNumber`() {
        assertThat("011111-1111".isValidPhoneNumber()).isFalse()
    }

    @Test
    fun `isValidPhoneNumber()_01-111-1111_isInvalidNumber`() {
        assertThat("01-111-1111".isValidPhoneNumber()).isFalse()
    }

    @Test
    fun `isValidPhoneNumber()_010-111-!@11_isInvalidNumber`() {
        assertThat("010-111-!@11".isValidPhoneNumber()).isFalse()
    }

}