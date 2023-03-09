package exoesqueletor.co.edu.udea.compumovil.gr02_202301.lab1gr02

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DateFormat
import java.util.*


class ActividadPersonalDatos : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private lateinit var btnCambiar: Button
    private lateinit var tvFechaNacimiento: TextView
    private val calendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_personal_data)

        btnCambiar = findViewById(R.id.btn_cambiar)
        tvFechaNacimiento = findViewById(R.id.tv_fechaNacimiento)

        btnCambiar.setOnClickListener {
            showDatePickerDialog()
        }
    }
    private fun showDatePickerDialog() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(this, this, year, month, day)
        datePickerDialog.show()
    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        val currentDateString = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.time)
        tvFechaNacimiento.text = " $currentDateString"
    }
}

