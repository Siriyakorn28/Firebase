package com.example.firehot2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnclear.setOnClickListener(){
            firstname.setText("")
            lastname.setText("")
        }

        btnsend.setOnClickListener(){
            val first = firstname.text.toString()
            val last = lastname.text.toString()

            val firebase = FirebaseDatabase.getInstance()
            val ref = firebase.getReference("Employee")
            val id:String? = ref.push().key

            val Employee = Employee(id.toString(),first,last)
            ref.child(id.toString()).setValue(Employee).addOnCompleteListener {
                Toast.makeText(applicationContext, "Complete", Toast.LENGTH_LONG).show()
                firstname.setText("")
                lastname.setText("")
            }
        }
    }
}