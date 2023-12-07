package com.example.sohag_job2_profile_registration_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sohag_job2_profile_registration_application.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var studentDatabase: StudentDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        studentDatabase = StudentDatabase.getDatabase(this)
        binding.saveBtn.setOnClickListener {
            saveData()
        }

        binding.deleteallBtn.setOnClickListener {
            GlobalScope.launch {
                studentDatabase.studentDao().deleteAll()
            }
        }
    }


    private suspend fun displayData(student: Student) {
        withContext(Dispatchers.Main){
            binding.firstNameET.setText(student.firstName.toString())
            binding.lastNameET.setText(student.lastName.toString())
            binding.rollNoET.setText(student.rollNo.toString())
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun saveData() {
        val firstName = binding.firstNameET.text.toString()
        val lastName = binding.lastNameET.text.toString()
        val rollNo = binding.rollNoET.text.toString()

        if(firstName.isNotEmpty() && lastName.isNotEmpty() && rollNo.isNotEmpty()){
            val student = Student(null, firstName, lastName, rollNo.toInt())
            GlobalScope.launch (Dispatchers.IO ){
                studentDatabase.studentDao().insert(student)
            }
            binding.firstNameET.text?.clear()
            binding.lastNameET.text?.clear()
            binding.rollNoET.text?.clear()

            Toast.makeText(this@MainActivity,"Data saved", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this@MainActivity,"Please enter all the data", Toast.LENGTH_SHORT).show()
        }

    }
}