package com.example.poo_1.datePicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.poo_1.databinding.ActivityDatePickerBinding

class DateTimePickerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDatePickerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatePickerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }

    private fun initUi() {
        binding.etDate.setOnClickListener{ showDatePickerDialog() }
        binding.etTime.setOnClickListener { showTimePickerDialog() }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment( {day, month,year -> onDateSelected(day,month,year)})
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.etDate.setText("haz seleccionado el dia $day, mes $month, año $year")
    }


    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment { onTimeSelected(it) }
        timePicker.show(supportFragmentManager, "timePicker")
    }

    private fun onTimeSelected(time: String) {
        binding.etTime.setText("Reserva para las $time")
    }
}