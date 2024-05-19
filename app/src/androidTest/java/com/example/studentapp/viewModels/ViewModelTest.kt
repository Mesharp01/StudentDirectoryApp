package com.example.studentapp.viewModels

import com.example.studentapp.data.Student
import com.example.studentapp.data.StudentHelper
import com.example.studentapp.viewModel.StudentViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Assert
import org.junit.Test

class ViewModelTest {

    private val viewModel = StudentViewModel()

    @Test
    fun studentViewModel_AddStudent() {
        viewModel.addStudent(Student("name", "email", "phoneNum"))
        assertEquals(1, viewModel.listAllStudents().size)
    }

    @Test
    fun studentViewModel_RemoveStudent() {
        var student = Student("name", "email", "phoneNum")
        viewModel.addStudent(student)
        viewModel.removeStudent(student)
        assertEquals(0, viewModel.listAllStudents().size)
    }

    @Test
    fun studentViewModel_ListAllStudents() {
        var student = Student("name", "email", "phoneNum")
        viewModel.addStudent(student)
        var students = viewModel.listAllStudents()
        var studentsCheck = listOf<Student>(student)
        assertEquals(studentsCheck, students)
    }

    @Test
    fun studentViewModel_ListAllStudents_EmptyList() {
        var students = viewModel.listAllStudents()
        assertTrue(students.isEmpty())
    }

    @Test
    fun studentViewModel_FindStudent_ReturnsTrue() {
        var student = Student("name", "email", "phoneNum")
        viewModel.addStudent(student)
        var foundStudent = viewModel.findStudent("email")
        assertEquals(Student("name", "email", "phoneNum"), foundStudent)
    }

    @Test
    fun studentViewModel_FindStudent_ReturnsFalse() {
        var student = Student("name", "email", "phoneNum")
        viewModel.addStudent(student)
        var foundStudent = viewModel.findStudent("notFound")
        assertEquals(null, foundStudent)
    }

    @Test
    fun studentViewModel_FilterStudentsByEmail_ReturnsTrue() {
        viewModel.addStudent(Student("name", "email", "phoneNum"))
        var foundStudents = viewModel.filterStudents("email")
        assertEquals(listOf<Student>(Student("name", "email", "phoneNum")), foundStudents)
    }

    @Test
    fun studentViewModel_FilterStudentsByName_ReturnsTrue() {
        viewModel.addStudent(Student("name", "email", "phoneNum"))
        var foundStudents = viewModel.filterStudents("name")
        assertEquals(listOf<Student>(Student("name", "email", "phoneNum")), foundStudents)
    }

    @Test
    fun studentViewModel_FilterStudentsByPhone_ReturnsTrue() {
        viewModel.addStudent(Student("name", "email", "phoneNum"))
        var foundStudents = viewModel.filterStudents("phoneNum")
        assertEquals(listOf<Student>(Student("name", "email", "phoneNum")), foundStudents)
    }

    @Test
    fun studentViewModel_FilterStudentsByEmail_ReturnsFalse() {
        viewModel.addStudent(Student("name", "email", "phoneNum"))
        var foundStudents = viewModel.filterStudents("notFound")
        assertEquals(listOf<Student>(), foundStudents)
    }

}