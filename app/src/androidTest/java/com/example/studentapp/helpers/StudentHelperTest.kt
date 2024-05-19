package com.example.studentapp.helpers

import com.example.studentapp.data.StudentHelper
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class StudentHelperTest {
    @Test fun emailValidator_CorrectEmail_ReturnsTrue() {
        assertTrue(StudentHelper.isValidEmail("name@name.com"))
    }

    @Test fun emailValidator_IncorrectEmail_ReturnsFalse() {
        assertFalse(StudentHelper.isValidEmail("name@"))
    }

    @Test fun emailValidator_IncorrectEmailChallenge_ReturnsFalse() {
        assertFalse(StudentHelper.isValidEmail("name@.com"))
    }

    @Test fun emailValidator_BlankEmail_ReturnsFalse() {
        assertFalse(StudentHelper.isValidEmail(""))
    }

    @Test fun nameValidator_CorrectName_ReturnsTrue() {
        assertTrue(StudentHelper.isValidName("Name Name"))
    }

    @Test fun nameValidator_IncorrectNameSymbols_ReturnsFalse() {
        assertFalse(StudentHelper.isValidName("Test Test!"))
    }

    @Test fun nameValidator_IncorrectNameNumbers_ReturnsFalse() {
        assertFalse(StudentHelper.isValidName("Test 123"))
    }

    @Test fun nameValidator_IncorrectNameNumbersAndSymbols_ReturnsFalse() {
        assertFalse(StudentHelper.isValidName("@Test 123!"))
    }

    @Test fun nameValidator_BlankName_ReturnsFalse() {
        assertFalse(StudentHelper.isValidName(""))
    }

    @Test fun phoneValidator_CorrectPhoneCountryCode_ReturnsTrue() {
        assertTrue(StudentHelper.isValidPhoneNum("+1-234-234-2344"))
    }

    @Test fun phoneValidator_CorrectPhoneAreaCode_ReturnsTrue() {
        assertTrue(StudentHelper.isValidPhoneNum("234-234-2344"))
    }

    @Test fun phoneValidator_CorrectPhone_ReturnsTrue() {
        assertTrue(StudentHelper.isValidPhoneNum("234-2344"))
    }

    @Test fun phoneValidator_CorrectPhoneNoDashes_ReturnsTrue() {
        assertTrue(StudentHelper.isValidPhoneNum("2342344"))
    }

    @Test fun phoneValidator_IncorrectPhoneIncomplete_ReturnsFalse() {
        assertFalse(StudentHelper.isValidPhoneNum("123-"))
    }

    @Test fun phoneValidator_IncorrectPhoneWrongCountryCode_ReturnsFalse() {
        assertFalse(StudentHelper.isValidPhoneNum("-1234-234-2344"))
    }

    @Test fun phoneNumValidator_IncorrectStudentEmptyPhoneNum_ReturnsFalse() {
        assertFalse(StudentHelper.isValidPhoneNum(""))
    }

    @Test fun studentValidator_CorrectStudent_ReturnsTrue() {
        assertTrue(StudentHelper.isValidStudent("Test Test", "test@test.com", "+1-234-234-2344"))
    }

    @Test fun studentValidator_IncorrectStudentAllFields_ReturnsFalse() {
        assertFalse(StudentHelper.isValidStudent("@#$%!", "email", "123-"))
    }

    @Test fun studentValidator_IncorrectStudentBadName_ReturnsFalse() {
        assertFalse(StudentHelper.isValidStudent("@#$%!", "email@email.com", "123-123-1233"))
    }

    @Test fun studentValidator_IncorrectStudentBadEmail_ReturnsFalse() {
        assertFalse(StudentHelper.isValidStudent("Name Test", "email", "123-123-1233"))
    }

    @Test fun studentValidator_IncorrectStudentBadPhoneNum_ReturnsFalse() {
        assertFalse(StudentHelper.isValidStudent("Name Test", "email@email.com", "54321234-"))
    }

    @Test fun studentValidator_IncorrectStudentEmptyFields_ReturnsFalse() {
        assertFalse(StudentHelper.isValidStudent("", "", ""))
    }
}