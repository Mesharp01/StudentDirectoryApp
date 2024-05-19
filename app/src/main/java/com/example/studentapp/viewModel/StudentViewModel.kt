package com.example.studentapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.studentapp.data.Student

class StudentViewModel() : ViewModel() {

    private var students: MutableList<Student> = mutableListOf()

    fun addStudent(student: Student){
        students.add(student)
    }

    fun removeStudent(student: Student){
        var index = students.indexOf(student)
        students.removeAt(index)
    }

    fun listAllStudents(): List<Student>{
        return students.toList()
    }

    fun filterStudents(input: String): List<Student>{
        return students.filter() {
            (it.email.contains(input, ignoreCase = true) || it.phoneNum.contains(input, ignoreCase = true) || it.name.contains(input, ignoreCase = true))
        }
    }

    fun findStudent(input: String?): Student?{
        if(input != null){
            return students.find {
            (it.email.contains(input, ignoreCase = true))
        }}
        else{
            return null
        }
    }

}

